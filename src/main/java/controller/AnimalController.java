package controller;

import dao.AnimalDAO;
import dao.JaulaDAO;
import java.util.List;
import model.AnimalAereoPreHistorico;
import model.AnimalAquaticoPreHistorico;
import model.AnimalPreHistorico;
import model.AnimalTerrestrePreHistorico;
import model.Dieta;
import model.Jaula;
import model.Porte;

public class AnimalController {

    private AnimalDAO animalDAO;
    private JaulaDAO jaulaDAO;

    public AnimalController() {
        animalDAO = new AnimalDAO();
        jaulaDAO = new JaulaDAO();
    }

    public String cadastrarAnimal(
        String tipo,
        String nome,
        String especie,
        Dieta dieta,
        Porte porte,
        String valorEspecifico
    ) {
        return cadastrarAnimal(
            tipo,
            gerarCodigoInternoAnimal(),
            nome,
            especie,
            dieta,
            porte,
            valorEspecifico
        );
    }

    public String cadastrarAnimal(
        String tipo,
        String codigo,
        String nome,
        String especie,
        Dieta dieta,
        Porte porte,
        String valorEspecifico
    ) {
        try {
            if (campoVazio(tipo) || campoVazio(codigo) || campoVazio(nome) || campoVazio(especie)) {
                return "Preencha todos os campos obrigatorios.";
            }

            if (!nomeValido(nome)) {
                return "O nome deve conter apenas letras e espacos.";
            }

            if (dieta == null || porte == null) {
                return "Selecione dieta e porte.";
            }

            int valor = Integer.parseInt(valorEspecifico);
            if (valor < 0 || valor > 10) {
                return "O atributo especifico deve estar entre 0 e 10.";
            }

            AnimalPreHistorico animal = criarAnimal(
                tipo,
                codigo,
                nome,
                especie,
                dieta,
                porte,
                valor
            );

            if (animal == null) {
                return "Tipo de animal invalido.";
            }

            animalDAO.salvar(animal);
            return "Animal cadastrado com sucesso. ID/Codigo: " + animal.getId() + ".";
        }
        catch (NumberFormatException exception) {
            return "O atributo especifico deve ser um numero inteiro.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel cadastrar o animal.";
        }
    }

    public String listarAnimais() {
        try {
            List<AnimalPreHistorico> animais = animalDAO.listarTodos();

            if (animais.isEmpty()) {
                return "Nenhum animal cadastrado.";
            }

            List<Jaula> jaulas = jaulaDAO.listarTodas();
            StringBuilder listagem = new StringBuilder();
            listagem.append("Animais cadastrados:\n\n");

            for (AnimalPreHistorico animal : animais) {
                listagem.append("ID/Codigo do animal: ")
                    .append(animal.getId())
                    .append("\n");
                listagem.append("Nome: ")
                    .append(animal.getNome())
                    .append("\n");
                listagem.append("Especie: ")
                    .append(animal.getEspecie())
                    .append("\n");
                listagem.append("Dieta: ")
                    .append(animal.getDieta())
                    .append("\n");
                listagem.append("Porte: ")
                    .append(animal.getPorte())
                    .append("\n");
                listagem.append("Tipo: ")
                    .append(animal.getClass().getSimpleName())
                    .append("\n");
                listagem.append("Grau de perigo: ")
                    .append(animal.getGrauPerigo())
                    .append("\n");
                listagem.append("Jaula: ")
                    .append(obterJaulaDoAnimal(animal, jaulas))
                    .append("\n\n");
            }

            return listagem.toString();
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel listar os animais.";
        }
    }

    public String excluirAnimal(String idTexto) {
        try {
            if (campoVazio(idTexto)) {
                return "Informe o id do animal para excluir.";
            }

            Long id = Long.parseLong(idTexto);
            AnimalPreHistorico animal = animalDAO.buscarPorId(id);

            if (animal == null) {
                return "Animal nao encontrado.";
            }

            if (animalEstaAlocado(id)) {
                return "Animal de id " + id + " esta alocado em uma jaula. Remova da jaula antes de excluir.";
            }

            animalDAO.remover(id);
            return "Animal de id " + id + " excluido com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "O id do animal deve ser numerico.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel excluir o animal.";
        }
    }

    private AnimalPreHistorico criarAnimal(
        String tipo,
        String codigo,
        String nome,
        String especie,
        Dieta dieta,
        Porte porte,
        int valorEspecifico
    ) {
        if ("TERRESTRE".equals(tipo)) {
            return new AnimalTerrestrePreHistorico(
                codigo,
                nome,
                especie,
                dieta,
                porte,
                valorEspecifico
            );
        }

        if ("AQUATICO".equals(tipo)) {
            return new AnimalAquaticoPreHistorico(
                codigo,
                nome,
                especie,
                dieta,
                porte,
                valorEspecifico
            );
        }

        if ("AEREO".equals(tipo)) {
            return new AnimalAereoPreHistorico(
                codigo,
                nome,
                especie,
                dieta,
                porte,
                valorEspecifico
            );
        }

        return null;
    }

    private String obterJaulaDoAnimal(AnimalPreHistorico animal, List<Jaula> jaulas) {
        if (animal.getId() == null) {
            return "sem jaula";
        }

        for (Jaula jaula : jaulas) {
            for (AnimalPreHistorico animalAlocado : jaula.getAnimaisAlocados()) {
                if (
                    animalAlocado.getId() != null &&
                    animalAlocado.getId().equals(animal.getId())
                ) {
                    return "id " + jaula.getId() +
                        " - numeracao " + jaula.getNumeracao() +
                        " (" + jaula.getClass().getSimpleName() + ")";
                }
            }
        }

        return "sem jaula";
    }

    private boolean animalEstaAlocado(Long idAnimal) {
        List<Jaula> jaulas = jaulaDAO.listarTodas();

        for (Jaula jaula : jaulas) {
            for (AnimalPreHistorico animal : jaula.getAnimaisAlocados()) {
                if (animal.getId() != null && animal.getId().equals(idAnimal)) {
                    return true;
                }
            }
        }

        return false;
    }

    private String gerarCodigoInternoAnimal() {
        return String.valueOf(System.currentTimeMillis()) +
            String.valueOf(System.nanoTime()).replace("-", "");
    }

    private boolean nomeValido(String nome) {
        for (int indice = 0; indice < nome.length(); indice++) {
            char caractere = nome.charAt(indice);

            if (!Character.isLetter(caractere) && caractere != ' ') {
                return false;
            }
        }

        return true;
    }

    private boolean campoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
