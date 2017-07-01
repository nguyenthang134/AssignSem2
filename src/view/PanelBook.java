package view;

import javax.swing.JTabbedPane;

public class PanelBook extends JTabbedPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelBook pbook;
	private BookViewJPanel book;
    private AuthorViewPanel author;
    private CategoriesViewPanel category;
    private PublisherViewPanel publisher;
    
    public PanelBook(){
    	book = new BookViewJPanel();
        author = new AuthorViewPanel();
        category = new CategoriesViewPanel();
        publisher = new PublisherViewPanel();
        this.setBounds(0, 0, 1200, 725);
		this.addTab("Book", book);
		this.addTab("Author", author);
		this.addTab("Category", category);
		this.addTab("Publisher", publisher);
    }
    
	public PanelBook getPbook() {
		return pbook;
	}

	public void setPbook(PanelBook pbook) {
		this.pbook = pbook;
	}
}
