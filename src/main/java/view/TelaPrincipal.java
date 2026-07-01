package view;

import controller.AnimalController;
import controller.TelaPrincipalController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Dieta;
import model.Porte;

public class TelaPrincipal extends JFrame {

    private TelaPrincipalController controller;
    private AnimalController animalController;
    private JTabbedPane abas;
    private JLabel titulo;
    private JComboBox<String> comboTipoAnimal;
    private JTextField campoCodigoAnimal;
    private JTextField campoNomeAnimal;
    private JTextField campoEspecieAnimal;
    private JComboBox<Dieta> comboDietaAnimal;
    private JComboBox<Porte> comboPorteAnimal;
    private JTextField campoAtributoEspecifico;
    private JTextField campoIdExcluirAnimal;
    private JButton botaoCadastrarAnimal;
    private JButton botaoListarAnimais;
    private JButton botaoExcluirAnimal;
    private JTextArea areaAnimais;
    private JTextArea areaJaulas;
    private JTextArea areaAlocacoes;
    private JTextArea areaSobre;

    public TelaPrincipal() {
        this(new TelaPrincipalController());
    }

    public TelaPrincipal(TelaPrincipalController controller) {
        this.controller = controller;
        animalController = new AnimalController();
        configurarJanela();
        inicializarComponentes();
        montarLayout();
        registrarEventos();
    }

    private void configurarJanela() {
        setTitle("Sistema de Gestao Jurassic Park");
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        titulo = new JLabel("Sistema de Gestao de Animais Pre-Historicos", SwingConstants.CENTER);
        abas = new JTabbedPane();

        comboTipoAnimal = new JComboBox<String>(new String[] {"TERRESTRE", "AQUATICO", "AEREO"});
        campoCodigoAnimal = new JTextField();
        campoNomeAnimal = new JTextField();
        campoEspecieAnimal = new JTextField();
        comboDietaAnimal = new JComboBox<Dieta>(Dieta.values());
        comboPorteAnimal = new JComboBox<Porte>(Porte.values());
        campoAtributoEspecifico = new JTextField();
        campoIdExcluirAnimal = new JTextField();

        botaoCadastrarAnimal = new JButton("Cadastrar animal");
        botaoListarAnimais = new JButton("Listar animais");
        botaoExcluirAnimal = new JButton("Excluir animal");

        areaAnimais = criarAreaTexto("Use os botoes para cadastrar, listar ou excluir animais.");
        areaJaulas = criarAreaTexto("Funcionalidade sera implementada na proxima etapa.");
        areaAlocacoes = criarAreaTexto("Funcionalidade sera implementada na proxima etapa.");
        areaSobre = criarAreaTexto(controller.obterMensagemSobre());
    }

    private JTextArea criarAreaTexto(String textoInicial) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setText(textoInicial);
        return area;
    }

    private void montarLayout() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        abas.addTab("Animais", criarAbaAnimais());
        abas.addTab("Jaulas", criarAbaSimples(areaJaulas));
        abas.addTab("Alocacoes", criarAbaSimples(areaAlocacoes));
        abas.addTab("Sobre", criarAbaSimples(areaSobre));

        painelPrincipal.add(abas, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    private JPanel criarAbaAnimais() {
        JPanel painelAnimais = new JPanel(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(8, 2, 5, 5));
        painelFormulario.add(new JLabel("Tipo:"));
        painelFormulario.add(comboTipoAnimal);
        painelFormulario.add(new JLabel("Codigo:"));
        painelFormulario.add(campoCodigoAnimal);
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(campoNomeAnimal);
        painelFormulario.add(new JLabel("Especie:"));
        painelFormulario.add(campoEspecieAnimal);
        painelFormulario.add(new JLabel("Dieta:"));
        painelFormulario.add(comboDietaAnimal);
        painelFormulario.add(new JLabel("Porte:"));
        painelFormulario.add(comboPorteAnimal);
        painelFormulario.add(new JLabel("Atributo especifico (0 a 10):"));
        painelFormulario.add(campoAtributoEspecifico);
        painelFormulario.add(new JLabel("Id para excluir:"));
        painelFormulario.add(campoIdExcluirAnimal);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 10, 10));
        painelBotoes.add(botaoCadastrarAnimal);
        painelBotoes.add(botaoListarAnimais);
        painelBotoes.add(botaoExcluirAnimal);

        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.add(painelFormulario, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        painelAnimais.add(painelSuperior, BorderLayout.NORTH);
        painelAnimais.add(new JScrollPane(areaAnimais), BorderLayout.CENTER);

        return painelAnimais;
    }

    private JPanel criarAbaSimples(JTextArea areaTexto) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        return painel;
    }

    private void registrarEventos() {
        botaoCadastrarAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAnimais.setText(animalController.cadastrarAnimal(
                    (String) comboTipoAnimal.getSelectedItem(),
                    campoCodigoAnimal.getText(),
                    campoNomeAnimal.getText(),
                    campoEspecieAnimal.getText(),
                    (Dieta) comboDietaAnimal.getSelectedItem(),
                    (Porte) comboPorteAnimal.getSelectedItem(),
                    campoAtributoEspecifico.getText()
                ));
            }
        });

        botaoListarAnimais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAnimais.setText(animalController.listarAnimais());
            }
        });

        botaoExcluirAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAnimais.setText(animalController.excluirAnimal(campoIdExcluirAnimal.getText()));
            }
        });
    }
}
