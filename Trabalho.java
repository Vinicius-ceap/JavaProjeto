import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.ParseException;
import javax.swing.table.TableModel;
import javax.swing.JFileChooser;
import java.io.File;  


public class Tabela extends JPanel {
	private JPanel pnPrincipal,pnTable,pnFilme;
	private JButton btAssistir,btEnviar,btImagem;
	private JScrollPane scrollTable;
	private JTable table;
	private JTextField tfCodigo,tfNome,tfAno;
	private JComboBox cbGenero;
	private JLabel lbCodigo,lbNome,lbAno,lbDisponibilidade,lbCadastroFilm,lbGenero;
	private JRadioButton rbSim, rbNao;
	private ButtonGroup btGroup;
	private MaskFormatter msAno;
	private JLabel lbNomeFilm,lbGeneroFilm,lbAnoFilm;

	public Tabela() {
		inicializarComponentes();
		definirEventos();
	}
	
	private void inicializarComponentes() {
		setLayout(null);
		lbCadastroFilm = new JLabel("Cadastro de Filme");
		lbCodigo = new JLabel("Codigo");
		lbNome = new JLabel("Nome");
		lbAno = new JLabel("Ano");
		lbGenero = new JLabel("Genero");
		lbDisponibilidade = new JLabel("Disponibilidade");
		tfCodigo = new JTextField(8);
		tfNome = new JTextField(8);
		tfAno = new JTextField(8);
		rbSim = new JRadioButton("Sim");
		rbNao = new JRadioButton("Nao");
		btGroup = new ButtonGroup();
		btEnviar = new JButton("Enviar");
		btImagem = new JButton("Adailtom");
		
		lbNomeFilm = new JLabel("Nome");
		lbGeneroFilm = new JLabel("Genero");
		lbAnoFilm = new JLabel("Ano");
		try {
		msAno = new MaskFormatter("##/##/####");
		tfAno = new JFormattedTextField(msAno);
		}catch(ParseException erro){
			erro.printStackTrace();
		}
		btGroup.add(rbSim);
		btGroup.add(rbNao);
		String[] cbItens = {"","Lutinha","Soquinho","Carros rapido","Carros Lentos"};
		cbGenero = new JComboBox(cbItens);
		
		rbSim.setSelected(true);
		
		rbNao.setBounds(220, 105, 50, 20);
		btEnviar.setBounds(200, 390, 70, 20);
		add(btEnviar);
		add(rbNao);
		pnPrincipal = new JPanel(new GridLayout(6,2));
		pnPrincipal.add(lbCodigo);
		pnPrincipal.add(tfCodigo);
		pnPrincipal.add(lbNome);
		pnPrincipal.add(tfNome);
		pnPrincipal.add(lbAno);
		pnPrincipal.add(tfAno);
		
		JPanel pnPanel1 = new JPanel(new GridLayout(5,0));
		pnPanel1.add(lbGenero);
		pnPanel1.add(cbGenero);
		pnPanel1.add(lbDisponibilidade);
		pnPanel1.add(rbSim);
		
		pnFilme = new JPanel(new GridLayout(4,0));
		pnFilme.add(lbNomeFilm);
		pnFilme.add(lbGeneroFilm);
		pnFilme.add(lbAnoFilm);
		pnFilme.add(btImagem);
		
		pnPrincipal.setBounds(5, 0,150, 150);
		pnPanel1.setBounds(170, 45, 90, 100);
		pnFilme.setBounds(300, 0,90, 70);
		add(pnFilme);
		add(pnPrincipal);
		add(pnPanel1);
		
		pnTable = new JPanel(new BorderLayout());
		pnTable.setBorder(new TitledBorder("Filmes"));
		scrollTable = new JScrollPane();
		
		DefaultTableModel tableModel = new DefaultTableModel(
				new String[] {"COD","Nome","Genero","Ano","Disp."},0) {
			public boolean isCellEditable(int row, int col){
				if(col == 0) {
					return false;
				}
				return true;
			}
		};
		table = new JTable(tableModel);
		DefaultTableCellRenderer alinhaEsquerda = new DefaultTableCellRenderer(); 
		alinhaEsquerda.setHorizontalAlignment(SwingConstants.LEFT);//a variável alinhaEsquerda alinha o conteudo da tabela a esquerda 
		table.getColumnModel().getColumn(0).setPreferredWidth(50); //define o tamanho da coluna
		table.getColumnModel().getColumn(0).setResizable(true);//define se a coluna é editavel ou não ;
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(true);
		table.getColumnModel().getColumn(1).setCellRenderer(alinhaEsquerda);//alinha o conteúdo da ceula a esquerda;
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaEsquerda);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alinhaEsquerda);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setCellRenderer(alinhaEsquerda);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getTableHeader().setReorderingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// linhas 97 e 98 define que as colunas não podem ser reorganizadas com arraste e solte
		scrollTable.setViewportView(table);
		pnTable.add(scrollTable);
		pnTable.setBounds(0, 160, 470, 230);
		add(pnTable);
		
		
		
	}
	
	public void Limpar() {
		tfCodigo.setText("");
		tfNome.setText("");
		tfAno.setText("");
		cbGenero.setSelectedIndex(0);
	}
	
	public void ImportImage() {
		
		JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	int result = fileChooser.showOpenDialog(null);
    	if (result == JFileChooser.APPROVE_OPTION) {
    	    File selectedFile = fileChooser.getSelectedFile();
    	    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    	}
	}
	
	private void definirEventos(){
		btEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfCodigo.getSelectedText() == ""|| tfNome.getText() == "" || cbGenero.getSelectedItem() == ""|| tfAno.getSelectedText() == "") {
					JOptionPane.showMessageDialog(pnTable,"Porfavor, informe os campos desejados");
					return;
				}
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				if(rbSim.isSelected()) {
					dtm.addRow(new Object[] {tfCodigo.getText(),tfNome.getText(),cbGenero.getSelectedItem(),tfAno.getText(),"Sim"});
				}if(rbNao.isSelected()) {
					dtm.addRow(new Object[] {tfCodigo.getText(),tfNome.getText(),cbGenero.getSelectedItem(),tfAno.getText(),"Nâo"});
				}
				//Limpar();
			}
		});
		
		btImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ImportImage();
			}

		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				lbNomeFilm.setText(model.getValueAt(selectedRowIndex, 1).toString());
				lbGeneroFilm.setText(model.getValueAt(selectedRowIndex, 2).toString());
				lbAnoFilm.setText(model.getValueAt(selectedRowIndex, 3).toString());			
				}
		});
	}
		
	
	
	public static void main(String args[]){
 		JFrame frame = new JFrame("Area de Texto");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(new Tabela());
		 frame.setBounds(300,300,900,500);
		 frame.setVisible(true);
	 }
	
}
