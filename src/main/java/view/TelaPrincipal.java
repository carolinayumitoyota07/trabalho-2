package view;

import controller.AnimalController;
import controller.AlocacaoController;
import controller.JaulaController;
import controller.TelaPrincipalController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Dieta;
import model.NivelSeguranca;
import model.Porte;

public class TelaPrincipal extends JFrame {

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
    private JComboBox<String> comboTipoJaula;
    private JTextField campoNumeracaoJaula;
    private JTextField campoCapacidadeJaula;
    private JComboBox<NivelSeguranca> comboNivelSegurancaJaula;
    private JTextField campoAtributoEspecificoJaula;
    private JTextField campoIdExcluirJaula;
    private JButton botaoCadastrarJaula;
    private JButton botaoListarJaulas;
    private JButton botaoExcluirJaula;
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
        setTitle("Sistema de Gestao Jurassic Park");
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        titulo = new JLabel("Sistema de Gestao de Animais Pre-Historicos", SwingConstants.CENTER);
        abas = new JTabbedPane();

        comboTipoAnimal = new JComboBox<String>(new String[] {"TERRESTRE", "AQUATICO", "AEREO"});
        campoNomeAnimal = new JTextField();
        campoEspecieAnimal = new JTextField();
        comboDietaAnimal = new JComboBox<Dieta>(Dieta.values());
        comboPorteAnimal = new JComboBox<Porte>(Porte.values());
        rotuloAtributoAnimal = new JLabel("Forca fisica (0 a 10):");
        campoAtributoEspecifico = new JTextField();

        botaoCadastrarAnimal = new JButton("Cadastrar animal");
        botaoListarAnimais = new JButton("Listar animais");
        botaoExcluirAnimal = new JButton("Excluir animal");

        comboTipoJaula = new JComboBox<String>(new String[] {"TERRESTRE", "AQUATICA", "AEREA"});
        campoNumeracaoJaula = new JTextField();
        campoCapacidadeJaula = new JTextField();
        comboNivelSegurancaJaula = new JComboBox<NivelSeguranca>(NivelSeguranca.values());
        campoAtributoEspecificoJaula = new JTextField();
        campoIdExcluirJaula = new JTextField();

        botaoCadastrarJaula = new JButton("Cadastrar jaula");
        botaoListarJaulas = new JButton("Listar jaulas");
        botaoExcluirJaula = new JButton("Excluir jaula");

        campoIdAnimalAlocacao = new JTextField();
        campoIdJaulaAlocacao = new JTextField();
        campoIdJaulaDestinoAlocacao = new JTextField();
        botaoAlocarAnimal = new JButton("Alocar animal na jaula");
        botaoRemoverAnimalDaJaula = new JButton("Remover animal da jaula");
        botaoTrocarAnimalDeJaula = new JButton("Trocar animal de jaula");
        botaoListarAlocacoes = new JButton("Listar alocacoes");

        areaAnimais = criarAreaTexto("Cadastre, liste ou exclua animais. O ID gerado pelo banco sera usado como codigo.");
        areaJaulas = criarAreaTexto("Cadastre, liste ou exclua jaulas. As operacoes usam o id da jaula.");
        areaAlocacoes = criarAreaTexto(obterMensagemOrientacaoAlocacoes());
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
        abas.addTab("Jaulas", criarAbaJaulas());
        abas.addTab("Alocacoes", criarAbaAlocacoes());

        painelPrincipal.add(abas, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    private JPanel criarAbaAnimais() {
        JPanel painelAnimais = new JPanel(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        painelFormulario.add(new JLabel("Tipo:"));
        painelFormulario.add(comboTipoAnimal);
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(campoNomeAnimal);
        painelFormulario.add(new JLabel("Especie:"));
        painelFormulario.add(campoEspecieAnimal);
        painelFormulario.add(new JLabel("Dieta:"));
        painelFormulario.add(comboDietaAnimal);
        painelFormulario.add(new JLabel("Porte:"));
        painelFormulario.add(comboPorteAnimal);
        painelFormulario.add(rotuloAtributoAnimal);
        painelFormulario.add(campoAtributoEspecifico);

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

    private JPanel criarAbaJaulas() {
        JPanel painelJaulas = new JPanel(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        painelFormulario.add(new JLabel("Tipo:"));
        painelFormulario.add(comboTipoJaula);
        painelFormulario.add(new JLabel("Numeracao:"));
        painelFormulario.add(campoNumeracaoJaula);
        painelFormulario.add(new JLabel("Capacidade:"));
        painelFormulario.add(campoCapacidadeJaula);
        painelFormulario.add(new JLabel("Nivel de seguranca:"));
        painelFormulario.add(comboNivelSegurancaJaula);
        painelFormulario.add(new JLabel("Atributo especifico (> 0):"));
        painelFormulario.add(campoAtributoEspecificoJaula);
        painelFormulario.add(new JLabel("Id da jaula para excluir:"));
        painelFormulario.add(campoIdExcluirJaula);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 10, 10));
        painelBotoes.add(botaoCadastrarJaula);
        painelBotoes.add(botaoListarJaulas);
        painelBotoes.add(botaoExcluirJaula);

        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.add(painelFormulario, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        painelJaulas.add(painelSuperior, BorderLayout.NORTH);
        painelJaulas.add(new JScrollPane(areaJaulas), BorderLayout.CENTER);

        return painelJaulas;
    }

    private JPanel criarAbaAlocacoes() {
        JPanel painelAlocacoes = new JPanel(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        painelFormulario.add(new JLabel("Id do animal:"));
        painelFormulario.add(campoIdAnimalAlocacao);
        painelFormulario.add(new JLabel("Id da jaula origem (remover/trocar):"));
        painelFormulario.add(campoIdJaulaAlocacao);
        painelFormulario.add(new JLabel("Id da jaula destino (alocar/trocar):"));
        painelFormulario.add(campoIdJaulaDestinoAlocacao);

        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 10, 10));
        painelBotoes.add(botaoAlocarAnimal);
        painelBotoes.add(botaoRemoverAnimalDaJaula);
        painelBotoes.add(botaoTrocarAnimalDeJaula);
        painelBotoes.add(botaoListarAlocacoes);

        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.add(painelFormulario, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        painelAlocacoes.add(painelSuperior, BorderLayout.NORTH);
        painelAlocacoes.add(new JScrollPane(areaAlocacoes), BorderLayout.CENTER);

        return painelAlocacoes;
    }

    private void registrarEventos() {
        comboTipoAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                atualizarRotuloAtributoAnimal();
            }
        });

        botaoCadastrarAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAnimais.setText(animalController.cadastrarAnimal(
                    (String) comboTipoAnimal.getSelectedItem(),
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
                String idAnimal = JOptionPane.showInputDialog(
                    TelaPrincipal.this,
                    "Informe o id do animal a ser excluido"
                );

                if (idAnimal == null) {
                    areaAnimais.setText("Exclusao de animal cancelada.");
                }
                else {
                    areaAnimais.setText(animalController.excluirAnimal(idAnimal));
                }
            }
        });

        botaoCadastrarJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaJaulas.setText(jaulaController.cadastrarJaula(
                    (String) comboTipoJaula.getSelectedItem(),
                    campoNumeracaoJaula.getText(),
                    campoCapacidadeJaula.getText(),
                    (NivelSeguranca) comboNivelSegurancaJaula.getSelectedItem(),
                    campoAtributoEspecificoJaula.getText()
                ));
            }
        });

        botaoListarJaulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaJaulas.setText(jaulaController.listarJaulas());
            }
        });

        botaoExcluirJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaJaulas.setText(jaulaController.excluirJaula(campoIdExcluirJaula.getText()));
            }
        });

        botaoAlocarAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAlocacoes.setText(alocacaoController.alocarAnimal(
                    campoIdAnimalAlocacao.getText(),
                    campoIdJaulaDestinoAlocacao.getText()
                ));
            }
        });

        botaoRemoverAnimalDaJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAlocacoes.setText(alocacaoController.removerAnimalDaJaula(
                    campoIdAnimalAlocacao.getText(),
                    campoIdJaulaAlocacao.getText()
                ));
            }
        });

        botaoTrocarAnimalDeJaula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAlocacoes.setText(alocacaoController.trocarAnimalDeJaula(
                    campoIdAnimalAlocacao.getText(),
                    campoIdJaulaAlocacao.getText(),
                    campoIdJaulaDestinoAlocacao.getText()
                ));
            }
        });

        botaoListarAlocacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaAlocacoes.setText(alocacaoController.listarAlocacoes());
            }
        });
    }

    private void atualizarRotuloAtributoAnimal() {
        String tipo = (String) comboTipoAnimal.getSelectedItem();

        if ("AQUATICO".equals(tipo)) {
            rotuloAtributoAnimal.setText("Dificuldade de contencao aquatica (0 a 10):");
        }
        else if ("AEREO".equals(tipo)) {
            rotuloAtributoAnimal.setText("Risco de fuga aerea (0 a 10):");
        }
        else {
            rotuloAtributoAnimal.setText("Forca fisica (0 a 10):");
        }
    }

    private String obterMensagemOrientacaoAlocacoes() {
        return "Alocar: informe o id do animal e o id da jaula de destino.\n"
            + "Remover: informe o id do animal e o id da jaula de origem.\n"
            + "Trocar: informe o id do animal, o id da jaula de origem e o id da jaula de destino.";
    }
}
