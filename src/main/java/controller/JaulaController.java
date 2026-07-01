package controller;

import dao.JaulaDAO;
import java.util.List;
import model.Jaula;
import model.JaulaAerea;
import model.JaulaAquatica;
import model.JaulaTerrestre;
import model.NivelSeguranca;

public class JaulaController {

    private JaulaDAO jaulaDAO;

    public JaulaController() {
        jaulaDAO = new JaulaDAO();
    }

    public String cadastrarJaula(
        String tipo,
        String numeracao,
        String capacidadeTexto,
        NivelSeguranca nivelSeguranca,
        String valorEspecifico
    ) {
        try {
            if (campoVazio(tipo) || campoVazio(numeracao) || campoVazio(capacidadeTexto)) {
                return "Preencha todos os campos obrigatorios.";
            }

            if (nivelSeguranca == null) {
                return "Selecione o nivel de seguranca.";
            }

            int capacidade = Integer.parseInt(capacidadeTexto);
            double valor = Double.parseDouble(valorEspecifico);

            if (capacidade <= 0) {
                return "A capacidade deve ser maior que zero.";
            }

            if (valor <= 0) {
                return "O atributo especifico deve ser maior que zero.";
            }

            Jaula jaula = criarJaula(
                tipo,
                numeracao,
                capacidade,
                nivelSeguranca,
                valor
            );

            if (jaula == null) {
                return "Tipo de jaula invalido.";
            }

            jaulaDAO.salvar(jaula);
            return "Jaula cadastrada com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "Capacidade e atributo especifico devem ser numericos.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel cadastrar a jaula.";
        }
    }

    public String listarJaulas() {
        try {
            List<Jaula> jaulas = jaulaDAO.listarTodas();

            if (jaulas.isEmpty()) {
                return "Nenhuma jaula cadastrada.";
            }

            StringBuilder listagem = new StringBuilder();
            listagem.append("Jaulas cadastradas:\n\n");

            for (Jaula jaula : jaulas) {
                listagem.append("Tipo: ")
                    .append(jaula.getClass().getSimpleName())
                    .append("\n");
                listagem.append("Id: ")
                    .append(jaula.getId())
                    .append("\n");
                listagem.append("Numeracao: ")
                    .append(jaula.getNumeracao())
                    .append("\n");
                listagem.append("Capacidade: ")
                    .append(jaula.getCapacidade())
                    .append("\n");
                listagem.append("Nivel de seguranca: ")
                    .append(jaula.getNivelSeguranca())
                    .append("\n");
                listagem.append("Animais alocados: ")
                    .append(jaula.getAnimaisAlocados().size())
                    .append("\n\n");
            }

            return listagem.toString();
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel listar as jaulas.";
        }
    }

    public String excluirJaula(String idTexto) {
        try {
            if (campoVazio(idTexto)) {
                return "Informe o id da jaula para excluir.";
            }

            Long id = Long.parseLong(idTexto);
            Jaula jaula = jaulaDAO.buscarPorId(id);

            if (jaula == null) {
                return "Jaula nao encontrada.";
            }

            if (!jaula.getAnimaisAlocados().isEmpty()) {
                return "Jaula possui animais alocados. Remova os animais antes de excluir.";
            }

            jaulaDAO.remover(id);
            return "Jaula excluida com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "O id deve ser numerico.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel excluir a jaula.";
        }
    }

    private Jaula criarJaula(
        String tipo,
        String numeracao,
        int capacidade,
        NivelSeguranca nivelSeguranca,
        double valorEspecifico
    ) {
        if ("TERRESTRE".equals(tipo)) {
            return new JaulaTerrestre(
                numeracao,
                capacidade,
                nivelSeguranca,
                valorEspecifico
            );
        }

        if ("AQUATICA".equals(tipo)) {
            return new JaulaAquatica(
                numeracao,
                capacidade,
                nivelSeguranca,
                valorEspecifico
            );
        }

        if ("AEREA".equals(tipo)) {
            return new JaulaAerea(
                numeracao,
                capacidade,
                nivelSeguranca,
                valorEspecifico
            );
        }

        return null;
    }

    private boolean campoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
