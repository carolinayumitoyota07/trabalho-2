package controller;

import dao.AnimalDAO;
import dao.JaulaDAO;
import java.util.List;
import model.AnimalPreHistorico;
import model.Jaula;

public class AlocacaoController {

    private AnimalDAO animalDAO;
    private JaulaDAO jaulaDAO;

    public AlocacaoController() {
        animalDAO = new AnimalDAO();
        jaulaDAO = new JaulaDAO();
    }

    public String alocarAnimal(String idAnimalTexto, String idJaulaTexto) {
        try {
            if (campoVazio(idAnimalTexto) || campoVazio(idJaulaTexto)) {
                return "Informe o id do animal e o id da jaula de destino.";
            }

            Long idAnimal = Long.parseLong(idAnimalTexto);
            Long idJaula = Long.parseLong(idJaulaTexto);

            AnimalPreHistorico animal = animalDAO.buscarPorId(idAnimal);
            if (animal == null) {
                return "Animal nao encontrado.";
            }

            Jaula jaula = jaulaDAO.buscarPorId(idJaula);
            if (jaula == null) {
                return "Jaula de destino nao encontrada.";
            }

            if (animalJaAlocado(idAnimal)) {
                return "Animal de id " + idAnimal + " ja esta alocado. Use a troca de jaula para mover o animal.";
            }

            int quantidadeAntes = jaula.getAnimaisAlocados().size();
            jaula.adicionarAnimal(animal);

            if (jaula.getAnimaisAlocados().size() == quantidadeAntes) {
                return montarMensagemFalhaAlocacao(jaula, animal);
            }

            jaulaDAO.atualizar(jaula);
            return "Animal de id " + idAnimal + " alocado com sucesso na jaula de id " + idJaula + ".";
        }
        catch (NumberFormatException exception) {
            return "Operacao nao realizada: os ids devem ser numericos.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel alocar o animal na jaula.";
        }
    }

    public String listarAlocacoes() {
        try {
            List<Jaula> jaulas = jaulaDAO.listarTodas();

            if (jaulas.isEmpty()) {
                return "Nenhuma jaula cadastrada.";
            }

            StringBuilder listagem = new StringBuilder();
            listagem.append("Alocacoes cadastradas:\n\n");

            for (Jaula jaula : jaulas) {
                listagem.append("Id da jaula: ")
                    .append(jaula.getId())
                    .append("\n");
                listagem.append("Tipo da jaula: ")
                    .append(jaula.getClass().getSimpleName())
                    .append("\n");
                listagem.append("Numeracao informada: ")
                    .append(jaula.getNumeracao())
                    .append("\n");
                listagem.append("Capacidade: ")
                    .append(jaula.getCapacidade())
                    .append("\n");
                listagem.append("Quantidade de animais alocados: ")
                    .append(jaula.getAnimaisAlocados().size())
                    .append("\n");

                if (jaula.getAnimaisAlocados().isEmpty()) {
                    listagem.append("Sem animais alocados.\n\n");
                }
                else {
                    listagem.append("Animais alocados:\n");

                    for (AnimalPreHistorico animal : jaula.getAnimaisAlocados()) {
                        listagem.append("- ID/Codigo do animal: ")
                            .append(animal.getId())
                            .append("\n");
                        listagem.append("  Nome: ")
                            .append(animal.getNome())
                            .append("\n");
                        listagem.append("  Especie: ")
                            .append(animal.getEspecie())
                            .append("\n");
                        listagem.append("  Tipo do animal: ")
                            .append(animal.getClass().getSimpleName())
                            .append("\n");
                        listagem.append("  Grau de perigo: ")
                            .append(animal.getGrauPerigo())
                            .append("\n");
                    }

                    listagem.append("\n");
                }
            }

            return listagem.toString();
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel listar as alocacoes.";
        }
    }

    public String removerAnimalDaJaula(String idAnimalTexto, String idJaulaTexto) {
        try {
            if (campoVazio(idAnimalTexto) || campoVazio(idJaulaTexto)) {
                return "Informe o id do animal e o id da jaula de origem.";
            }

            Long idAnimal = Long.parseLong(idAnimalTexto);
            Long idJaula = Long.parseLong(idJaulaTexto);

            AnimalPreHistorico animal = animalDAO.buscarPorId(idAnimal);
            if (animal == null) {
                return "Animal nao encontrado.";
            }

            Jaula jaula = jaulaDAO.buscarPorId(idJaula);
            if (jaula == null) {
                return "Jaula de origem nao encontrada.";
            }

            if (!animalEstaNaJaula(jaula, idAnimal)) {
                return "Animal de id " + idAnimal + " nao esta alocado na jaula de id " + idJaula + ".";
            }

            AnimalPreHistorico animalAlocado = obterAnimalAlocado(jaula, idAnimal);
            int quantidadeAntes = jaula.getAnimaisAlocados().size();
            jaula.removerAnimal(animalAlocado);

            if (jaula.getAnimaisAlocados().size() == quantidadeAntes) {
                return "Nao foi possivel remover o animal da jaula.";
            }

            jaulaDAO.atualizar(jaula);
            return "Animal de id " + idAnimal + " removido da jaula de id " + idJaula + " com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "Operacao nao realizada: os ids devem ser numericos.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel remover o animal da jaula.";
        }
    }

    public String trocarAnimalDeJaula(
        String idAnimalTexto,
        String idJaulaOrigemTexto,
        String idJaulaDestinoTexto
    ) {
        try {
            if (
                campoVazio(idAnimalTexto) ||
                campoVazio(idJaulaOrigemTexto) ||
                campoVazio(idJaulaDestinoTexto)
            ) {
                return "Informe o id do animal, o id da jaula de origem e o id da jaula de destino.";
            }

            Long idAnimal = Long.parseLong(idAnimalTexto);
            Long idJaulaOrigem = Long.parseLong(idJaulaOrigemTexto);
            Long idJaulaDestino = Long.parseLong(idJaulaDestinoTexto);

            if (idJaulaOrigem.equals(idJaulaDestino)) {
                return "A jaula de origem deve ser diferente da jaula de destino.";
            }

            AnimalPreHistorico animal = animalDAO.buscarPorId(idAnimal);
            if (animal == null) {
                return "Animal nao encontrado.";
            }

            Jaula jaulaOrigem = jaulaDAO.buscarPorId(idJaulaOrigem);
            if (jaulaOrigem == null) {
                return "Jaula de origem nao encontrada.";
            }

            Jaula jaulaDestino = jaulaDAO.buscarPorId(idJaulaDestino);
            if (jaulaDestino == null) {
                return "Jaula de destino nao encontrada.";
            }

            if (!animalEstaNaJaula(jaulaOrigem, idAnimal)) {
                return "Animal de id " + idAnimal + " nao esta alocado na jaula de origem.";
            }

            AnimalPreHistorico animalAlocado = obterAnimalAlocado(jaulaOrigem, idAnimal);
            int quantidadeDestinoAntes = jaulaDestino.getAnimaisAlocados().size();
            jaulaDestino.adicionarAnimal(animalAlocado);

            if (jaulaDestino.getAnimaisAlocados().size() == quantidadeDestinoAntes) {
                return montarMensagemFalhaAlocacao(jaulaDestino, animalAlocado);
            }

            int quantidadeOrigemAntes = jaulaOrigem.getAnimaisAlocados().size();
            jaulaOrigem.removerAnimal(animalAlocado);

            if (jaulaOrigem.getAnimaisAlocados().size() == quantidadeOrigemAntes) {
                return "Nao foi possivel remover o animal da jaula de origem.";
            }

            jaulaDAO.atualizarDuasJaulas(jaulaOrigem, jaulaDestino);
            return "Animal de id " + idAnimal + " trocado da jaula de id " +
                idJaulaOrigem + " para a jaula de id " + idJaulaDestino + " com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "Operacao nao realizada: os ids devem ser numericos.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel trocar o animal de jaula.";
        }
    }

    private boolean animalJaAlocado(Long idAnimal) {
        List<Jaula> jaulas = jaulaDAO.listarTodas();

        for (Jaula jaula : jaulas) {
            if (animalEstaNaJaula(jaula, idAnimal)) {
                return true;
            }
        }

        return false;
    }

    private boolean animalEstaNaJaula(Jaula jaula, Long idAnimal) {
        return obterAnimalAlocado(jaula, idAnimal) != null;
    }

    private AnimalPreHistorico obterAnimalAlocado(Jaula jaula, Long idAnimal) {
        for (AnimalPreHistorico animal : jaula.getAnimaisAlocados()) {
            if (animal.getId() != null && animal.getId().equals(idAnimal)) {
                return animal;
            }
        }

        return null;
    }

    private String montarMensagemFalhaAlocacao(Jaula jaula, AnimalPreHistorico animal) {
        if (!jaula.verificarCapacidadeDisponivel()) {
            return "Operacao nao realizada: a jaula esta cheia.";
        }

        if (!jaula.verificarCompatibilidadeAnimal(animal)) {
            return "Operacao nao realizada: animal incompativel com a jaula.";
        }

        return "Operacao nao realizada: animal nao foi alocado nesta jaula.";
    }

    private boolean campoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
