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
                return "Informe o id do animal e o id da jaula.";
            }

            Long idAnimal = Long.parseLong(idAnimalTexto);
            Long idJaula = Long.parseLong(idJaulaTexto);

            AnimalPreHistorico animal = animalDAO.buscarPorId(idAnimal);
            if (animal == null) {
                return "Animal nao encontrado.";
            }

            Jaula jaula = jaulaDAO.buscarPorId(idJaula);
            if (jaula == null) {
                return "Jaula nao encontrada.";
            }

            if (animalJaAlocado(idAnimal)) {
                return "Animal ja esta alocado. A troca de jaula sera implementada depois.";
            }

            int quantidadeAntes = jaula.getAnimaisAlocados().size();
            jaula.adicionarAnimal(animal);

            if (jaula.getAnimaisAlocados().size() == quantidadeAntes) {
                return montarMensagemFalhaAlocacao(jaula, animal);
            }

            jaulaDAO.atualizar(jaula);
            return "Animal alocado com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "Os ids devem ser numericos.";
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
                listagem.append("Jaula: ")
                    .append(jaula.getClass().getSimpleName())
                    .append("\n");
                listagem.append("Id da jaula: ")
                    .append(jaula.getId())
                    .append("\n");
                listagem.append("Numeracao: ")
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
                        listagem.append("- ")
                            .append(animal.getClass().getSimpleName())
                            .append("\n");
                        listagem.append("  Id: ")
                            .append(animal.getId())
                            .append("\n");
                        listagem.append("  Codigo: ")
                            .append(animal.getCodigo())
                            .append("\n");
                        listagem.append("  Nome: ")
                            .append(animal.getNome())
                            .append("\n");
                        listagem.append("  Especie: ")
                            .append(animal.getEspecie())
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
                return "Informe o id do animal e o id da jaula.";
            }

            Long idAnimal = Long.parseLong(idAnimalTexto);
            Long idJaula = Long.parseLong(idJaulaTexto);

            AnimalPreHistorico animal = animalDAO.buscarPorId(idAnimal);
            if (animal == null) {
                return "Animal nao encontrado.";
            }

            Jaula jaula = jaulaDAO.buscarPorId(idJaula);
            if (jaula == null) {
                return "Jaula nao encontrada.";
            }

            if (!animalEstaNaJaula(jaula, idAnimal)) {
                return "Animal nao esta alocado nesta jaula.";
            }

            AnimalPreHistorico animalAlocado = obterAnimalAlocado(jaula, idAnimal);
            int quantidadeAntes = jaula.getAnimaisAlocados().size();
            jaula.removerAnimal(animalAlocado);

            if (jaula.getAnimaisAlocados().size() == quantidadeAntes) {
                return "Nao foi possivel remover o animal da jaula.";
            }

            jaulaDAO.atualizar(jaula);
            return "Animal removido da jaula com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "Os ids devem ser numericos.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel remover o animal da jaula.";
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
            return "Nao foi possivel alocar: a jaula esta cheia.";
        }

        if (!jaula.verificarCompatibilidadeAnimal(animal)) {
            return "Nao foi possivel alocar: o animal e incompativel com esta jaula.";
        }

        return "Nao foi possivel alocar o animal nesta jaula.";
    }

    private boolean campoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
