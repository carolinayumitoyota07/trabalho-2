package controller;

import dao.AnimalDAO;
import java.util.List;
import model.AnimalPreHistorico;

public class TelaPrincipalController {

    private AnimalDAO animalDAO;

    public TelaPrincipalController() {
        animalDAO = new AnimalDAO();
    }

    public String obterMensagemAnimais() {
        return "A area de animais sera implementada nas proximas etapas.";
    }

    public String obterListagemAnimais() {
        try {
            List<AnimalPreHistorico> animais = animalDAO.listarTodos();

            if (animais.isEmpty()) {
                return "Nenhum animal cadastrado no banco.";
            }

            StringBuilder listagem = new StringBuilder();
            listagem.append("Animais cadastrados no banco:\n\n");

            for (AnimalPreHistorico animal : animais) {
                listagem.append("Tipo: ")
                    .append(animal.getClass().getSimpleName())
                    .append("\n");
                listagem.append("Id: ")
                    .append(animal.getId())
                    .append("\n");
                listagem.append("Codigo: ")
                    .append(animal.getCodigo())
                    .append("\n");
                listagem.append("Nome: ")
                    .append(animal.getNome())
                    .append("\n");
                listagem.append("Dieta: ")
                    .append(animal.getDieta())
                    .append("\n");
                listagem.append("Porte: ")
                    .append(animal.getPorte())
                    .append("\n");
                listagem.append("Grau de perigo: ")
                    .append(animal.getGrauPerigo())
                    .append("\n\n");
            }

            return listagem.toString();
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel listar os animais cadastrados.";
        }
    }

    public String obterMensagemJaulas() {
        return "A area de jaulas sera implementada nas proximas etapas.";
    }

    public String obterMensagemSobre() {
        return "Sistema de Gestao de Animais Pre-Historicos. Projeto academico de Programacao Orientada a Objetos.";
    }

    public boolean confirmarSaida() {
        return true;
    }
}
