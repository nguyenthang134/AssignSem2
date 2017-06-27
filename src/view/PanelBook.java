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
		this.addTab("Sách", book);
		this.addTab("Tác giả", author);
		this.addTab("Thể loại", category);
		this.addTab("Nhà xuất bản", publisher);
    }
    
	public PanelBook getPbook() {
		return pbook;
	}

	public void setPbook(PanelBook pbook) {
		this.pbook = pbook;
	}
}
