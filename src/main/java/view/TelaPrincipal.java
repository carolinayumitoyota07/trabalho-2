package view;

import controller.TelaPrincipalController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TelaPrincipal extends JFrame {

    private TelaPrincipalController controller;
    private JLabel titulo;
    private JTextArea areaInformacoes;
    private JButton botaoAnimais;
    private JButton botaoJaulas;
    private JButton botaoSobre;
    private JButton botaoSair;

    public TelaPrincipal() {
        this(new TelaPrincipalController());
    }

    public TelaPrincipal(TelaPrincipalController controller) {
        this.controller = controller;
        configurarJanela();
        inicializarComponentes();
        montarLayout();
        registrarEventos();
    }

    private void configurarJanela() {
        setTitle("Sistema de Gestao Jurassic Park");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        titulo = new JLabel("Sistema de Gestao de Animais Pre-Historicos", SwingConstants.CENTER);

        areaInformacoes = new JTextArea();
        areaInformacoes.setEditable(false);
        areaInformacoes.setLineWrap(true);
        areaInformacoes.setWrapStyleWord(true);
        areaInformacoes.setText("Bem-vindo ao Sistema de Gestao de Animais Pre-Historicos.");

        botaoAnimais = new JButton("Animais");
        botaoJaulas = new JButton("Jaulas");
        botaoSobre = new JButton("Sobre");
        botaoSair = new JButton("Sair");
    }

    private void montarLayout() {
        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 10, 10));
        painelBotoes.add(botaoAnimais);
        painelBotoes.add(botaoJaulas);
        painelBotoes.add(botaoSobre);
        painelBotoes.add(botaoSair);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.add(titulo, BorderLayout.NORTH);
        painelPrincipal.add(new JScrollPane(areaInformacoes), BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void registrarEventos() {
        botaoAnimais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaInformacoes.setText(controller.obterMensagemAnimais());
            }
        });

        botaoJaulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaInformacoes.setText(controller.obterMensagemJaulas());
            }
        });

        botaoSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                areaInformacoes.setText(controller.obterMensagemSobre());
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controller.confirmarSaida()) {
                    dispose();
                }
            }
        });
    }
}
