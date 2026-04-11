package br.edu.cs.poo.ac.bolsa.telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaPrincipal() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Sistema da Bolsa de Valores");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(10, 30, 414, 30);
        contentPane.add(lblTitulo);

        
        JButton btnCadastrarAtivo = new JButton("Cadastrar Ativo");
        btnCadastrarAtivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                TelaCadastroAtivo telaAtivo = new TelaCadastroAtivo();
                telaAtivo.setVisible(true);
                telaAtivo.setLocationRelativeTo(null);
            }
        });
        btnCadastrarAtivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCadastrarAtivo.setBounds(110, 100, 220, 40);
        contentPane.add(btnCadastrarAtivo);

        
        JButton btnCadastrarEmpresa = new JButton("Cadastrar Empresa");
        btnCadastrarEmpresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        
                TelaCadastroEmpresa telaEmp = new TelaCadastroEmpresa();
                telaEmp.setVisible(true);
                telaEmp.setLocationRelativeTo(null);
            }
        });
        btnCadastrarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCadastrarEmpresa.setBounds(110, 160, 220, 40);
        contentPane.add(btnCadastrarEmpresa);

   
        JButton btnCadastrarPessoa = new JButton("Cadastrar Pessoa Física");
        btnCadastrarPessoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                TelaCadastroPessoa telaPes = new TelaCadastroPessoa();
                telaPes.setVisible(true);
                telaPes.setLocationRelativeTo(null);
            }
        });
        btnCadastrarPessoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCadastrarPessoa.setBounds(110, 220, 220, 40);
        contentPane.add(btnCadastrarPessoa);
    }
}