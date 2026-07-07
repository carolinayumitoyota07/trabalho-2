package view;

import controller.AnimalController;
import controller.AlocacaoController;
import controller.JaulaController;
import controller.TelaPrincipalController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import model.Dieta;
import model.NivelSeguranca;
import model.Porte;

public class TelaPrincipal extends JFrame {

    private static final Color COR_VERDE_ESCURO = new Color(24, 67, 48);
    private static final Color COR_VERDE_MEDIO = new Color(49, 104, 73);
    private static final Color COR_AMBAR = new Color(214, 161, 45);
    private static final Color COR_CARVAO = new Color(36, 42, 38);
    private static final Color COR_VERMELHO = new Color(145, 56, 48);
    private static final Color COR_FUNDO = new Color(235, 241, 230);
    private static final Color COR_PAINEL = new Color(253, 255, 250);
    private static final Color COR_RESULTADO = new Color(249, 252, 244);
    private static final Color COR_BORDA = new Color(176, 190, 165);
    private static final Font FONTE_TITULO = new Font("SansSerif", Font.BOLD, 22);
    private static final Font FONTE_SUBTITULO = new Font("SansSerif", Font.BOLD, 14);

    private TelaPrincipalController controller;
    private AnimalController animalController;
    private AlocacaoController alocacaoController;
    private JaulaController jaulaController;
    private JTabbedPane abas;
    private JLabel titulo;
    private JComboBox<String> comboTipoAnimal;
    private JTextField campoNomeAnimal;
    private JTextField campoEspecieAnimal;
    private JComboBox<Dieta> comboDietaAnimal;
    private JComboBox<Porte> comboPorteAnimal;
    private JLabel rotuloAtributoAnimal;
    private JTextField campoAtributoEspecifico;
    private JButton botaoCadastrarAnimal;
    private JButton botaoListarAnimais;
    private JButton botaoExcluirAnimal;
    private JTextField campoBuscaIdAnimal;
    private JTextField campoBuscaNomeAnimal;
    private JComboBox<String> comboFiltroTipoAnimal;
    private JButton botaoBuscarAnimais;
    private JButton botaoLimparBuscaAnimais;
    private JComboBox<String> comboTipoJaula;
    private JTextField campoNumeracaoJaula;
    private JTextField campoCapacidadeJaula;
    private JComboBox<NivelSeguranca> comboNivelSegurancaJaula;
    private JLabel rotuloAtributoJaula;
    private JTextField campoAtributoEspecificoJaula;
    private JButton botaoCadastrarJaula;
    private JButton botaoListarJaulas;
    private JButton botaoExcluirJaula;
    private JTextField campoBuscaIdJaula;
    private JComboBox<String> comboFiltroTipoJaula;
    private JButton botaoBuscarJaulas;
    private JButton botaoLimparBuscaJaulas;
    private JTextField campoIdAnimalAlocacao;
    private JTextField campoIdJaulaAlocacao;
    private JTextField campoIdJaulaDestinoAlocacao;
    private JButton botaoAlocarAnimal;
    private JButton botaoRemoverAnimalDaJaula;
    private JButton botaoTrocarAnimalDeJaula;
    private JButton botaoListarAlocacoes;
    private JTextArea areaAnimais;
    private JTextArea areaJaulas;
    private JTextArea areaAlocacoes;

    public TelaPrincipal() {
        this(new TelaPrincipalController());
    }

    public TelaPrincipal(TelaPrincipalController controller) {
        this.controller = controller;
        animalController = new AnimalController();
        alocacaoController = new AlocacaoController();
        jaulaController = new JaulaController();
        configurarJanela();
        inicializarComponentes();
        montarLayout();
        registrarEventos();
    }

    private void configurarJanela() {
        setTitle("Sistema de Gestão de Animais Pre-Históricos");
        setIconImage(AppIcone.criarImagem());
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        titulo = new JLabel("Sistema de Gestâo de Animais Pre-Históricos", SwingConstants.CENTER);
        abas = new JTabbedPane();

        comboTipoAnimal = new JComboBox<String>(new String[] {"TERRESTRE", "AQUÁTICO", "AÉREO"});
        campoNomeAnimal = new JTextField();
        campoEspecieAnimal = new JTextField();
        comboDietaAnimal = new JComboBox<Dieta>(Dieta.values());
        comboPorteAnimal = new JComboBox<Porte>(Porte.values());
        rotuloAtributoAnimal = new JLabel("Força física (0 a 10):");
        campoAtributoEspecifico = new JTextField();

        botaoCadastrarAnimal = new JButton("Cadastrar animal");
        botaoListarAnimais = new JButton("Listar animais");
        botaoExcluirAnimal = new JButton("Excluir animal");
        campoBuscaIdAnimal = new JTextField();
        campoBuscaNomeAnimal = new JTextField();
        comboFiltroTipoAnimal = new JComboBox<String>(
            new String[] {"Todos", "Terrestre", "Aquático", "Aéreo"}
        );
        botaoBuscarAnimais = new JButton("Buscar/filtrar animais");
        botaoLimparBuscaAnimais = new JButton("Limpar busca");

        comboTipoJaula = new JComboBox<String>(new String[] {"TERRESTRE", "AQUÁTICA", "AÉREA"});
        campoNumeracaoJaula = new JTextField();
        campoCapacidadeJaula = new JTextField();
        comboNivelSegurancaJaula = new JComboBox<NivelSeguranca>(NivelSeguranca.values());
        rotuloAtributoJaula = new JLabel("Metros quadrados:");
        campoAtributoEspecificoJaula = new JTextField();

        botaoCadastrarJaula = new JButton("Cadastrar jaula");
        botaoListarJaulas = new JButton("Listar jaulas");
        botaoExcluirJaula = new JButton("Excluir jaula");
        campoBuscaIdJaula = new JTextField();
        comboFiltroTipoJaula = new JComboBox<String>(
            new String[] {"Todos", "Terrestre", "Aquática", "Aérea"}
        );
        botaoBuscarJaulas = new JButton("Buscar/filtrar jaulas");
        botaoLimparBuscaJaulas = new JButton("Limpar busca");

        campoIdAnimalAlocacao = new JTextField();
        campoIdJaulaAlocacao = new JTextField();
        campoIdJaulaDestinoAlocacao = new JTextField();
        botaoAlocarAnimal = new JButton("Alocar");
        botaoRemoverAnimalDaJaula = new JButton("Remover");
        botaoTrocarAnimalDeJaula = new JButton("Trocar");
        botaoListarAlocacoes = new JButton("Listar alocações");

        areaAnimais = criarAreaTexto("Cadastre, liste ou exclua animais. O ID gerado pelo banco sera usado como código.");
        areaJaulas = criarAreaTexto("Cadastre, liste ou exclua jaulas. As operações usam o id da jaula.");
        areaAlocacoes = criarAreaTexto(obterMensagemOrientacaoAlocacoes());

        configurarAparenciaComponentes();
    }

    private JTextArea criarAreaTexto(String textoInicial) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setRows(12);
        area.setBackground(COR_RESULTADO);
        area.setForeground(COR_CARVAO);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setBorder(new EmptyBorder(8, 8, 8, 8));
        area.setText(textoInicial);
        return area;
    }

    private void montarLayout() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(12, 12, 12, 12));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);

        abas.addTab("Animais", criarAbaAnimais());
        abas.addTab("Jaulas", criarAbaJaulas());
        abas.addTab("Alocações", criarAbaAlocacoes());

        painelPrincipal.add(abas, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    private JPanel criarAbaAnimais() {
        JPanel painelAnimais = criarPainelAba();

        JPanel painelFormulario = criarPainelSecao("Dados do animal", new GridLayout(6, 2, 8, 8));
        painelFormulario.add(new JLabel("Tipo:"));
        painelFormulario.add(comboTipoAnimal);
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(campoNomeAnimal);
        painelFormulario.add(new JLabel("Espécie:"));
        painelFormulario.add(campoEspecieAnimal);
        painelFormulario.add(new JLabel("Dieta:"));
        painelFormulario.add(comboDietaAnimal);
        painelFormulario.add(new JLabel("Porte:"));
        painelFormulario.add(comboPorteAnimal);
        painelFormulario.add(rotuloAtributoAnimal);
        painelFormulario.add(campoAtributoEspecifico);

        JPanel painelBotoes = criarPainelSecao("Ações", new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.add(botaoCadastrarAnimal);
        painelBotoes.add(botaoListarAnimais);
        painelBotoes.add(botaoExcluirAnimal);

        JPanel painelCadastro = new JPanel(new BorderLayout(10, 10));
        painelCadastro.setOpaque(false);
        painelCadastro.add(painelFormulario, BorderLayout.CENTER);
        painelCadastro.add(painelBotoes, BorderLayout.SOUTH);

        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.setOpaque(false);
        painelSuperior.add(painelCadastro, BorderLayout.NORTH);
        painelSuperior.add(criarPainelBuscaAnimais(), BorderLayout.CENTER);

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setOpaque(false);
        painelConteudo.add(painelSuperior, BorderLayout.NORTH);
        painelConteudo.add(criarPainelResultado(areaAnimais), BorderLayout.CENTER);

        painelAnimais.add(criarTituloAba("Cadastro e consulta de animais"), BorderLayout.NORTH);
        painelAnimais.add(painelConteudo, BorderLayout.CENTER);

        return painelAnimais;
    }

    private JPanel criarAbaJaulas() {
        JPanel painelJaulas = criarPainelAba();

        JPanel painelFormulario = criarPainelSecao("Dados da jaula", new GridLayout(5, 2, 8, 8));
        painelFormulario.add(new JLabel("Tipo:"));
        painelFormulario.add(comboTipoJaula);
        painelFormulario.add(new JLabel("Numeração:"));
        painelFormulario.add(campoNumeracaoJaula);
        painelFormulario.add(new JLabel("Capacidade:"));
        painelFormulario.add(campoCapacidadeJaula);
        painelFormulario.add(new JLabel("Nivel de segurança:"));
        painelFormulario.add(comboNivelSegurancaJaula);
        painelFormulario.add(rotuloAtributoJaula);
        painelFormulario.add(campoAtributoEspecificoJaula);

        JPanel painelBotoes = criarPainelSecao("Ações", new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.add(botaoCadastrarJaula);
        painelBotoes.add(botaoListarJaulas);
        painelBotoes.add(botaoExcluirJaula);

        JPanel painelCadastro = new JPanel(new BorderLayout(10, 10));
        painelCadastro.setOpaque(false);
        painelCadastro.add(painelFormulario, BorderLayout.CENTER);
        painelCadastro.add(painelBotoes, BorderLayout.SOUTH);

        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.setOpaque(false);
        painelSuperior.add(painelCadastro, BorderLayout.NORTH);
        painelSuperior.add(criarPainelBuscaJaulas(), BorderLayout.CENTER);

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setOpaque(false);
        painelConteudo.add(painelSuperior, BorderLayout.NORTH);
        painelConteudo.add(criarPainelResultado(areaJaulas), BorderLayout.CENTER);

        painelJaulas.add(criarTituloAba("Cadastro e consulta de jaulas"), BorderLayout.NORTH);
        painelJaulas.add(painelConteudo, BorderLayout.CENTER);

        return painelJaulas;
    }

    private JPanel criarPainelBuscaAnimais() {
        JPanel painelBusca = criarPainelSecao("Busca e filtros", new BorderLayout(8, 8));

        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 8, 8));
        painelCampos.setOpaque(false);
        painelCampos.add(new JLabel("Buscar por ID:"));
        painelCampos.add(campoBuscaIdAnimal);
        painelCampos.add(new JLabel("Buscar por nome:"));
        painelCampos.add(campoBuscaNomeAnimal);
        painelCampos.add(new JLabel("Filtrar por tipo:"));
        painelCampos.add(comboFiltroTipoAnimal);

        JPanel painelBotoesBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoesBusca.setOpaque(false);
        painelBotoesBusca.add(botaoBuscarAnimais);
        painelBotoesBusca.add(botaoLimparBuscaAnimais);

        painelBusca.add(painelCampos, BorderLayout.CENTER);
        painelBusca.add(painelBotoesBusca, BorderLayout.SOUTH);

        return painelBusca;
    }

    private JPanel criarPainelBuscaJaulas() {
        JPanel painelBusca = criarPainelSecao("Busca e filtros", new BorderLayout(8, 8));

        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 8, 8));
        painelCampos.setOpaque(false);
        painelCampos.add(new JLabel("Buscar por ID:"));
        painelCampos.add(campoBuscaIdJaula);
        painelCampos.add(new JLabel("Filtrar por tipo:"));
        painelCampos.add(comboFiltroTipoJaula);

        JPanel painelBotoesBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoesBusca.setOpaque(false);
        painelBotoesBusca.add(botaoBuscarJaulas);
        painelBotoesBusca.add(botaoLimparBuscaJaulas);

        painelBusca.add(painelCampos, BorderLayout.CENTER);
        painelBusca.add(painelBotoesBusca, BorderLayout.SOUTH);

        return painelBusca;
    }

    private JPanel criarAbaAlocacoes() {
        JPanel painelAlocacoes = criarPainelAba();

        JPanel painelFormulario = criarPainelSecao("Operações de alocação", new GridLayout(3, 2, 8, 8));
        painelFormulario.add(new JLabel("Id do animal:"));
        painelFormulario.add(campoIdAnimalAlocacao);
        painelFormulario.add(new JLabel("Id da jaula origem (remover/trocar):"));
        painelFormulario.add(campoIdJaulaAlocacao);
        painelFormulario.add(new JLabel("Id da jaula destino (alocar/trocar):"));
        painelFormulario.add(campoIdJaulaDestinoAlocacao);

        JPanel painelBotoes = criarPainelSecao("Ações", new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.add(botaoAlocarAnimal);
        painelBotoes.add(botaoRemoverAnimalDaJaula);
        painelBotoes.add(botaoTrocarAnimalDeJaula);
        painelBotoes.add(botaoListarAlocacoes);

        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.setOpaque(false);
        painelSuperior.add(painelFormulario, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        JPanel painelConteudo = new JPanel(new BorderLayout(10, 10));
        painelConteudo.setOpaque(false);
        painelConteudo.add(painelSuperior, BorderLayout.NORTH);
        painelConteudo.add(criarPainelResultado(areaAlocacoes), BorderLayout.CENTER);

        painelAlocacoes.add(criarTituloAba("Alocação, remoção e troca de animais"), BorderLayout.NORTH);
        painelAlocacoes.add(painelConteudo, BorderLayout.CENTER);

        return painelAlocacoes;
    }

    private JPanel criarCabecalho() {
        JPanel painelCabecalho = new JPanel(new BorderLayout(5, 5));
        painelCabecalho.setBackground(COR_VERDE_ESCURO);
        painelCabecalho.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 4, 0, COR_AMBAR),
            new EmptyBorder(12, 12, 12, 12)
        ));

        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_AMBAR);

        JLabel subtitulo = new JLabel(
            "Controle de animais, jaulas e alocações",
            SwingConstants.CENTER
        );
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 12));
        subtitulo.setForeground(new Color(239, 244, 228));

        painelCabecalho.add(titulo, BorderLayout.CENTER);
        painelCabecalho.add(subtitulo, BorderLayout.SOUTH);

        return painelCabecalho;
    }

    private JPanel criarPainelAba() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return painel;
    }

    private JLabel criarTituloAba(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_SUBTITULO);
        label.setForeground(COR_VERDE_ESCURO);
        label.setBorder(new EmptyBorder(0, 2, 4, 2));
        return label;
    }

    private JPanel criarPainelSecao(String tituloSecao, java.awt.LayoutManager layout) {
        JPanel painel = new JPanel(layout);
        painel.setBackground(COR_PAINEL);
        painel.setBorder(criarBordaSecao(tituloSecao));
        return painel;
    }

    private JScrollPane criarPainelResultado(JTextArea areaTexto) {
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBorder(criarBordaSecao("Resultado"));
        return scrollPane;
    }

    private javax.swing.border.Border criarBordaSecao(String tituloSecao) {
        TitledBorder bordaTitulo = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA),
            tituloSecao
        );
        bordaTitulo.setTitleFont(new Font("SansSerif", Font.BOLD, 12));
        bordaTitulo.setTitleColor(COR_VERDE_ESCURO);

        return BorderFactory.createCompoundBorder(
            bordaTitulo,
            new EmptyBorder(8, 8, 8, 8)
        );
    }

    private void configurarAparenciaComponentes() {
        configurarBotaoPrincipal(botaoCadastrarAnimal);
        configurarBotaoNeutro(botaoListarAnimais);
        configurarBotaoExclusao(botaoExcluirAnimal);
        configurarBotaoPrincipal(botaoBuscarAnimais);
        configurarBotaoNeutro(botaoLimparBuscaAnimais);
        configurarBotaoPrincipal(botaoCadastrarJaula);
        configurarBotaoNeutro(botaoListarJaulas);
        configurarBotaoExclusao(botaoExcluirJaula);
        configurarBotaoPrincipal(botaoBuscarJaulas);
        configurarBotaoNeutro(botaoLimparBuscaJaulas);
        configurarBotaoPrincipal(botaoAlocarAnimal);
        configurarBotaoNeutro(botaoRemoverAnimalDaJaula);
        configurarBotaoPrincipal(botaoTrocarAnimalDeJaula);
        configurarBotaoNeutro(botaoListarAlocacoes);
    }

    private void configurarBotaoPrincipal(JButton botao) {
        configurarBotao(botao, COR_VERDE_MEDIO, Color.WHITE);
    }

    private void configurarBotaoNeutro(JButton botao) {
        configurarBotao(botao, new Color(222, 228, 215), COR_CARVAO);
    }

    private void configurarBotaoExclusao(JButton botao) {
        configurarBotao(botao, COR_VERMELHO, Color.WHITE);
    }

    private void configurarBotao(JButton botao, Color fundo, Color texto) {
        botao.setFocusPainted(false);
        botao.setBackground(fundo);
        botao.setForeground(texto);
        botao.setOpaque(true);
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_CARVAO),
            new EmptyBorder(5, 10, 5, 10)
        ));
    }

    private void mostrarAviso(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Aviso",
            JOptionPane.WARNING_MESSAGE
        );
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private boolean confirmarAcao(String mensagem) {
        int resposta = JOptionPane.showConfirmDialog(
            this,
            mensagem,
            "Confirmar ação",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        return resposta == JOptionPane.YES_OPTION;
    }

    private void tratarResultadoOperacao(String resultado, JTextArea areaResultado) {
        areaResultado.setText(resultado);

        if (textoVazio(resultado)) {
            return;
        }

        if (resultadoIndicaErroInesperado(resultado)) {
            mostrarErro(resultado);
        }
        else if (resultadoIndicaUsoIncorreto(resultado)) {
            mostrarAviso(resultado);
        }
    }

    private void tratarResultadoListagem(String resultado, JTextArea areaResultado) {
        areaResultado.setText(resultado);

        if (!textoVazio(resultado) && resultadoIndicaErroInesperado(resultado)) {
            mostrarErro(resultado);
        }
    }

    private void tratarResultadoBusca(String resultado, JTextArea areaResultado) {
        areaResultado.setText(resultado);

        if (textoVazio(resultado)) {
            return;
        }

        if (resultadoIndicaErroInesperado(resultado)) {
            mostrarErro(resultado);
        }
        else if (resultadoIndicaAvisoBusca(resultado)) {
            mostrarAviso(resultado);
        }
    }

    private boolean resultadoIndicaErroInesperado(String resultado) {
        String texto = normalizarTexto(resultado).toLowerCase();
        return texto.startsWith("erro") || texto.contains("nao foi possivel");
    }

    private boolean resultadoIndicaUsoIncorreto(String resultado) {
        String texto = resultado.toLowerCase();
        return !texto.contains("sucesso") &&
            !texto.contains("cancelada") &&
            !resultadoIndicaErroInesperado(resultado);
    }

    private boolean resultadoIndicaAvisoBusca(String resultado) {
        String texto = normalizarTexto(resultado).toLowerCase();
        return texto.contains("deve ser numerico") ||
            texto.startsWith("nenhum animal encontrado") ||
            texto.startsWith("nenhuma jaula encontrada");
    }

    private boolean textoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private boolean textoNumerico(String texto) {
        try {
            Long.parseLong(texto);
            return true;
        }
        catch (NumberFormatException exception) {
            return false;
        }
    }

    private void registrarEventos() {
        comboTipoAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                atualizarRotuloAtributoAnimal();
            }
        });

        comboTipoJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                atualizarRotuloAtributoJaula();
            }
        });

        botaoCadastrarAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String resultado = animalController.cadastrarAnimal(
                    obterTipoAnimalSelecionado(),
                    campoNomeAnimal.getText(),
                    campoEspecieAnimal.getText(),
                    (Dieta) comboDietaAnimal.getSelectedItem(),
                    (Porte) comboPorteAnimal.getSelectedItem(),
                    campoAtributoEspecifico.getText()
                );
                tratarResultadoOperacao(resultado, areaAnimais);
            }
        });

        botaoListarAnimais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                tratarResultadoListagem(animalController.listarAnimais(), areaAnimais);
            }
        });

        botaoBuscarAnimais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String resultado = animalController.buscarAnimais(
                    campoBuscaIdAnimal.getText(),
                    campoBuscaNomeAnimal.getText(),
                    obterFiltroTipoAnimalSelecionado()
                );
                tratarResultadoBusca(resultado, areaAnimais);
            }
        });

        botaoLimparBuscaAnimais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                limparBuscaAnimais();
            }
        });

        botaoExcluirAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String idAnimal = JOptionPane.showInputDialog(
                    TelaPrincipal.this,
                    "Informe o id do animal a ser excluído"
                );

                if (idAnimal == null) {
                    areaAnimais.setText("Exclusão de animal cancelada.");
                }
                else if (textoVazio(idAnimal) || !textoNumerico(idAnimal.trim())) {
                    tratarResultadoOperacao(animalController.excluirAnimal(idAnimal.trim()), areaAnimais);
                }
                else if (!confirmarAcao("Tem certeza que deseja excluir o animal de id " + idAnimal.trim() + "?")) {
                    areaAnimais.setText("Exclusão de animal cancelada.");
                }
                else {
                    tratarResultadoOperacao(animalController.excluirAnimal(idAnimal.trim()), areaAnimais);
                }
            }
        });

        botaoCadastrarJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String resultado = jaulaController.cadastrarJaula(
                    obterTipoJaulaSelecionada(),
                    campoNumeracaoJaula.getText(),
                    campoCapacidadeJaula.getText(),
                    (NivelSeguranca) comboNivelSegurancaJaula.getSelectedItem(),
                    campoAtributoEspecificoJaula.getText()
                );
                tratarResultadoOperacao(resultado, areaJaulas);
            }
        });

        botaoListarJaulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                tratarResultadoListagem(jaulaController.listarJaulas(), areaJaulas);
            }
        });

        botaoBuscarJaulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String resultado = jaulaController.buscarJaulas(
                    campoBuscaIdJaula.getText(),
                    obterFiltroTipoJaulaSelecionado()
                );
                tratarResultadoBusca(resultado, areaJaulas);
            }
        });

        botaoLimparBuscaJaulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                limparBuscaJaulas();
            }
        });

        botaoExcluirJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String idJaula = JOptionPane.showInputDialog(
                    TelaPrincipal.this,
                    "Informe o id da jaula a ser excluída"
                );

                if (idJaula == null) {
                    areaJaulas.setText("Exclusão de jaula cancelada.");
                }
                else if (textoVazio(idJaula) || !textoNumerico(idJaula.trim())) {
                    tratarResultadoOperacao(jaulaController.excluirJaula(idJaula.trim()), areaJaulas);
                }
                else if (!confirmarAcao("Tem certeza que deseja excluir a jaula de id " + idJaula.trim() + "?")) {
                    areaJaulas.setText("Exclusao de jaula cancelada.");
                }
                else {
                    tratarResultadoOperacao(jaulaController.excluirJaula(idJaula.trim()), areaJaulas);
                }
            }
        });

        botaoAlocarAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String resultado = alocacaoController.alocarAnimal(
                    campoIdAnimalAlocacao.getText(),
                    campoIdJaulaDestinoAlocacao.getText()
                );
                tratarResultadoOperacao(resultado, areaAlocacoes);
            }
        });

        botaoRemoverAnimalDaJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String resultado = alocacaoController.removerAnimalDaJaula(
                    campoIdAnimalAlocacao.getText(),
                    campoIdJaulaAlocacao.getText()
                );
                tratarResultadoOperacao(resultado, areaAlocacoes);
            }
        });

        botaoTrocarAnimalDeJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                String resultado = alocacaoController.trocarAnimalDeJaula(
                    campoIdAnimalAlocacao.getText(),
                    campoIdJaulaAlocacao.getText(),
                    campoIdJaulaDestinoAlocacao.getText()
                );
                tratarResultadoOperacao(resultado, areaAlocacoes);
            }
        });

        botaoListarAlocacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                tratarResultadoListagem(alocacaoController.listarAlocacoes(), areaAlocacoes);
            }
        });
    }

    private void atualizarRotuloAtributoAnimal() {
        String tipo = obterTipoAnimalSelecionado();

        if ("AQUATICO".equals(tipo)) {
            rotuloAtributoAnimal.setText("Dificuldade de contenção aquática (0 a 10):");
        }
        else if ("AEREO".equals(tipo)) {
            rotuloAtributoAnimal.setText("Risco de fuga aérea (0 a 10):");
        }
        else {
            rotuloAtributoAnimal.setText("Força física (0 a 10):");
        }
    }

    private void atualizarRotuloAtributoJaula() {
        String tipo = obterTipoJaulaSelecionada();

        if ("AQUATICA".equals(tipo)) {
            rotuloAtributoJaula.setText("Metros cúbicos de água:");
        }
        else if ("AEREA".equals(tipo)) {
            rotuloAtributoJaula.setText("Metros altura:");
        }
        else {
            rotuloAtributoJaula.setText("Metros quadrados:");
        }
    }

    private String obterMensagemOrientacaoAlocacoes() {
        return "Alocar: informe o id do animal e o id da jaula de destino.\n"
            + "Remover: informe o id do animal e o id da jaula de origem.\n"
            + "Trocar: informe o id do animal, o id da jaula de origem e o id da jaula de destino.";
    }

    private String obterTipoAnimalSelecionado() {
        return normalizarTipo((String) comboTipoAnimal.getSelectedItem());
    }

    private String obterFiltroTipoAnimalSelecionado() {
        return normalizarTipo((String) comboFiltroTipoAnimal.getSelectedItem());
    }

    private String obterTipoJaulaSelecionada() {
        return normalizarTipo((String) comboTipoJaula.getSelectedItem());
    }

    private String obterFiltroTipoJaulaSelecionado() {
        return normalizarTipo((String) comboFiltroTipoJaula.getSelectedItem());
    }

    private void limparBuscaAnimais() {
        campoBuscaIdAnimal.setText("");
        campoBuscaNomeAnimal.setText("");
        comboFiltroTipoAnimal.setSelectedIndex(0);
        tratarResultadoListagem(animalController.listarAnimais(), areaAnimais);
    }

    private void limparBuscaJaulas() {
        campoBuscaIdJaula.setText("");
        comboFiltroTipoJaula.setSelectedIndex(0);
        tratarResultadoListagem(jaulaController.listarJaulas(), areaJaulas);
    }

    private String normalizarTipo(String tipo) {
        return normalizarTexto(tipo).toUpperCase();
    }

    private String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }

        return Normalizer
            .normalize(texto.trim(), Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");
    }
}
