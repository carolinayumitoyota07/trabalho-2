package controller;

import dao.JaulaDAO;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.AnimalPreHistorico;
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

            if (!numeracaoValida(numeracao)) {
                return "A numeracao da jaula deve ser numerica.";
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
            return "Jaula cadastrada com sucesso. Id da jaula: " + jaula.getId() + ".";
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

            return montarListagemJaulas(jaulas, "Jaulas cadastradas:");
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel listar as jaulas.";
        }
    }

    public String buscarJaulas(String idTexto, String tipoFiltro) {
        try {
            String idBusca = idTexto == null ? "" : idTexto.trim();
            String tipoBusca = normalizarFiltroTipo(tipoFiltro);

            if (campoVazio(idBusca) && campoVazio(tipoBusca)) {
                return listarJaulas();
            }

            List<Jaula> jaulasEncontradas = new ArrayList<Jaula>();

            if (!campoVazio(idBusca)) {
                Long id = Long.parseLong(idBusca);
                Jaula jaula = jaulaDAO.buscarPorId(id);

                if (jaula != null && jaulaAtendeTipo(jaula, tipoBusca)) {
                    jaulasEncontradas.add(jaula);
                }
            }
            else {
                List<Jaula> jaulas = jaulaDAO.listarTodas();

                for (Jaula jaula : jaulas) {
                    if (jaulaAtendeTipo(jaula, tipoBusca)) {
                        jaulasEncontradas.add(jaula);
                    }
                }
            }

            if (jaulasEncontradas.isEmpty()) {
                return "Nenhuma jaula encontrada para os filtros informados.";
            }

            return montarListagemJaulas(jaulasEncontradas, "Jaulas encontradas:");
        }
        catch (NumberFormatException exception) {
            return "O id da jaula deve ser numerico.";
        }
        catch (RuntimeException exception) {
            return "Nao foi possivel buscar as jaulas.";
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
                return "Jaula de id " + id + " possui animais alocados. Remova os animais antes de excluir.";
            }

            jaulaDAO.remover(id);
            return "Jaula de id " + id + " excluida com sucesso.";
        }
        catch (NumberFormatException exception) {
            return "O id da jaula deve ser numerico.";
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

    private String montarListagemJaulas(List<Jaula> jaulas, String titulo) {
        StringBuilder listagem = new StringBuilder();
        listagem.append(titulo)
            .append("\n\n");

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
            listagem.append("Nivel de seguranca: ")
                .append(jaula.getNivelSeguranca())
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
                }

                listagem.append("\n");
            }
        }

        return listagem.toString();
    }

    private boolean jaulaAtendeTipo(Jaula jaula, String tipoBusca) {
        if (campoVazio(tipoBusca)) {
            return true;
        }

        if ("TERRESTRE".equals(tipoBusca)) {
            return jaula instanceof JaulaTerrestre;
        }

        if ("AQUATICA".equals(tipoBusca)) {
            return jaula instanceof JaulaAquatica;
        }

        if ("AEREA".equals(tipoBusca)) {
            return jaula instanceof JaulaAerea;
        }

        return false;
    }

    private String normalizarFiltroTipo(String tipoFiltro) {
        String tipo = normalizarTexto(tipoFiltro).toUpperCase(Locale.ROOT);

        if ("TODOS".equals(tipo)) {
            return "";
        }

        return tipo;
    }

    private String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }

        return Normalizer
            .normalize(texto.trim(), Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");
    }

    private boolean numeracaoValida(String numeracao) {
        for (int indice = 0; indice < numeracao.length(); indice++) {
            if (!Character.isDigit(numeracao.charAt(indice))) {
                return false;
            }
        }

        return true;
    }

    private boolean campoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
