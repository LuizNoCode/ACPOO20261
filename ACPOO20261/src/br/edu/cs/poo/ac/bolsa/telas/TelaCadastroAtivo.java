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

import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.entidade.FaixaRenda;
import br.edu.cs.poo.ac.bolsa.negocio.AtivoMediator;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class TelaCadastroAtivo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtCodigo;
    private JTextField txtDescricao;
    private JTextField txtValMin;
    private JTextField txtValMax;
    private JTextField txtTaxaMin;
    private JTextField txtTaxaMax;
    private JTextField txtPrazo;

    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastroAtivo frame = new TelaCadastroAtivo();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */

    public TelaCadastroAtivo() {
        setTitle("Cadastrar Novo Ativo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Cadastro de Ativo");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(10, 11, 364, 25);
        contentPane.add(lblTitulo);

        JLabel lblCod = new JLabel("Código:");
        lblCod.setBounds(20, 60, 100, 20);
        contentPane.add(lblCod);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(130, 60, 220, 20);
        contentPane.add(txtCodigo);

        JLabel lblDesc = new JLabel("Descrição:");
        lblDesc.setBounds(20, 100, 100, 20);
        contentPane.add(lblDesc);
        txtDescricao = new JTextField();
        txtDescricao.setBounds(130, 100, 220, 20);
        contentPane.add(txtDescricao);

        JLabel lblValMin = new JLabel("Valor Mín.:");
        lblValMin.setBounds(20, 140, 100, 20);
        contentPane.add(lblValMin);
        txtValMin = new JTextField();
        txtValMin.setBounds(130, 140, 220, 20);
        contentPane.add(txtValMin);

        JLabel lblValMax = new JLabel("Valor Máx.:");
        lblValMax.setBounds(20, 180, 100, 20);
        contentPane.add(lblValMax);
        txtValMax = new JTextField();
        txtValMax.setBounds(130, 180, 220, 20);
        contentPane.add(txtValMax);

        JLabel lblTaxaMin = new JLabel("Taxa Mín.:");
        lblTaxaMin.setBounds(20, 220, 100, 20);
        contentPane.add(lblTaxaMin);
        txtTaxaMin = new JTextField();
        txtTaxaMin.setBounds(130, 220, 220, 20);
        contentPane.add(txtTaxaMin);

        JLabel lblTaxaMax = new JLabel("Taxa Máx.:");
        lblTaxaMax.setBounds(20, 260, 100, 20);
        contentPane.add(lblTaxaMax);
        txtTaxaMax = new JTextField();
        txtTaxaMax.setBounds(130, 260, 220, 20);
        contentPane.add(txtTaxaMax);

        JLabel lblPrazo = new JLabel("Prazo (Meses):");
        lblPrazo.setBounds(20, 300, 100, 20);
        contentPane.add(lblPrazo);
        txtPrazo = new JTextField();
        txtPrazo.setBounds(130, 300, 220, 20);
        contentPane.add(txtPrazo);

        
        JButton btnSalvar = new JButton("Salvar Ativo");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    
                    long cod = Long.parseLong(txtCodigo.getText());
                    double vMin = Double.parseDouble(txtValMin.getText());
                    double vMax = Double.parseDouble(txtValMax.getText());
                    double tMin = Double.parseDouble(txtTaxaMin.getText());
                    double tMax = Double.parseDouble(txtTaxaMax.getText());
                    int prazo = Integer.parseInt(txtPrazo.getText());

                    
                    Ativo a = new Ativo();
                    a.setCodigo(cod);
                    a.setDescricao(txtDescricao.getText());
                    a.setValorMinimoAplicacao(vMin);
                    a.setValorMaximoAplicacao(vMax);
                    a.setTaxaMensalMinima(tMin);
                    a.setTaxaMensalMaxima(tMax);
                    a.setFaixaMinimaPermitida(FaixaRenda.REGULAR); 
                    a.setPrazoEmMeses(prazo);

                  
                    AtivoMediator mediator = AtivoMediator.getInstancia();
                    MensagensValidacao msgs = mediator.incluir(a);

                    
                    if (msgs.estaVazio()) {
                        JOptionPane.showMessageDialog(null, "Ativo cadastrado com sucesso!");
                        dispose(); 
                    } else {
                     
                        String erroGeral = "";
                        for(String erro : msgs.getMensagens()){
                            erroGeral += erro + "\n";
                        }
                        JOptionPane.showMessageDialog(null, erroGeral, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                   
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente com números.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSalvar.setBounds(130, 360, 150, 40);
        contentPane.add(btnSalvar);
    }
}