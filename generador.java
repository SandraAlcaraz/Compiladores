public class generador {
	
	public static void main(String [] args ){
		lexico l= new lexico();
        JFileChooser jFileChooser = new JFileChooser();
	int result = jFileChooser.showOpenDialog(null);
        File selectedFile = jFileChooser.getSelectedFile();
        if (result == JFileChooser.APPROVE_OPTION) {
            l.anlisisLexico(selectedFile);
        } else {
            System.out.println("Error selecting file");
        }
		
		
		
		
	} 