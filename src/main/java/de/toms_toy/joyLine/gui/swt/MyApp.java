package de.toms_toy.joyLine.gui.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;

public class MyApp {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MyApp window = new MyApp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("New Item");
		
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("New SubMenu");
		
		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);

	}
}
