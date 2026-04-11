package br.edu.cs.poo.ac.bolsa.telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.cs.poo.ac.bolsa.entidade.Contatos;
import br.edu.cs.poo.ac.bolsa.entidade.Endereco;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.negocio.InvestidorMediator;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class TelaCadastroPessoa extends JFrame {
   
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtCpf;
    private JTextField txtNome;
    private JTextField txtRenda;
    private JTextField txtData;
    private JTextField txtLogradouro;
    private JTextField txtNumero;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtPais;
    private JTextField txtEmail;
    private JTextField txtCelular;

    public TelaCadastroPessoa() {
        setTitle("Cadastrar Pessoa Física");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Cadastro de Investidor Pessoa");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(10, 11, 464, 25);
        contentPane.add(lblTitulo);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 60, 100, 20);
        contentPane.add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(120, 60, 330, 20);
        contentPane.add(txtCpf);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 90, 100, 20);
        contentPane.add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(120, 90, 330, 20);
        contentPane.add(txtNome);

        JLabel lblRenda = new JLabel("Renda:");
        lblRenda.setBounds(20, 120, 100, 20);
        contentPane.add(lblRenda);
        txtRenda = new JTextField();
        txtRenda.setBounds(120, 120, 100, 20);
        contentPane.add(txtRenda);

        JLabel lblData = new JLabel("Nascimento (dd/MM/yyyy):");
        lblData.setBounds(230, 120, 150, 20);
        contentPane.add(lblData);
        txtData = new JTextField();
        txtData.setBounds(380, 120, 70, 20);
        contentPane.add(txtData);

        JLabel lblEnd = new JLabel("--- Endereço ---");
        lblEnd.setBounds(20, 160, 200, 20);
        contentPane.add(lblEnd);

        JLabel lblLogradouro = new JLabel("Logradouro:");
        lblLogradouro.setBounds(20, 190, 100, 20);
        contentPane.add(lblLogradouro);
        txtLogradouro = new JTextField();
        txtLogradouro.setBounds(120, 190, 200, 20);
        contentPane.add(txtLogradouro);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(330, 190, 60, 20);
        contentPane.add(lblNumero);
        txtNumero = new JTextField();
        txtNumero.setBounds(390, 190, 60, 20);
        contentPane.add(txtNumero);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(20, 220, 100, 20);
        contentPane.add(lblCidade);
        txtCidade = new JTextField();
        txtCidade.setBounds(120, 220, 120, 20);
        contentPane.add(txtCidade);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(250, 220, 60, 20);
        contentPane.add(lblEstado);
        txtEstado = new JTextField();
        txtEstado.setBounds(310, 220, 40, 20);
        contentPane.add(txtEstado);

        JLabel lblPais = new JLabel("País:");
        lblPais.setBounds(360, 220, 40, 20);
        contentPane.add(lblPais);
        txtPais = new JTextField();
        txtPais.setBounds(400, 220, 50, 20);
        contentPane.add(txtPais);

        JLabel lblContatos = new JLabel("--- Contatos ---");
        lblContatos.setBounds(20, 260, 200, 20);
        contentPane.add(lblContatos);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(20, 290, 100, 20);
        contentPane.add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(120, 290, 330, 20);
        contentPane.add(txtEmail);

        JLabel lblCelular = new JLabel("Celular:");
        lblCelular.setBounds(20, 320, 100, 20);
        contentPane.add(lblCelular);
        txtCelular = new JTextField();
        txtCelular.setBounds(120, 320, 330, 20);
        contentPane.add(txtCelular);

        JButton btnSalvar = new JButton("Salvar Pessoa");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    InvestidorPessoa ip = new InvestidorPessoa();
                    ip.setCpf(txtCpf.getText());
                    ip.setNome(txtNome.getText());
                    ip.setRenda(Double.parseDouble(txtRenda.getText()));
                    ip.setDataNascimento(LocalDate.parse(txtData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                    Endereco end = new Endereco();
                    end.setLogradouro(txtLogradouro.getText());
                    end.setNumero(txtNumero.getText());
                    end.setCidade(txtCidade.getText());
                    end.setEstado(txtEstado.getText());
                    end.setPais(txtPais.getText());
                    ip.setEndereco(end);

                    Contatos cont = new Contatos();
                    cont.setEmail(txtEmail.getText());
                    cont.setTelefoneCelular(txtCelular.getText());
                    ip.setContatos(cont);

                    InvestidorMediator mediator = new InvestidorMediator();
                    MensagensValidacao msgs = mediator.incluirInvestidorPessoa(ip);

                    if (msgs.estaVazio()) {
                        JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
                        dispose();
                    } else {
                        String erroGeral = "";
                        for(String erro : msgs.getMensagens()){
                            erroGeral += erro + "\n";
                        }
                        JOptionPane.showMessageDialog(null, erroGeral, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente. Datas no formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSalvar.setBounds(160, 390, 150, 40);
        contentPane.add(btnSalvar);
    }
}