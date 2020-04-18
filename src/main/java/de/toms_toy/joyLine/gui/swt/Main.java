package de.toms_toy.joyLine.gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Main {

    protected Shell shell;
    private Table table;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Main window = new Main();
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
        shell.setLayout(new RowLayout(SWT.HORIZONTAL));

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLayoutData(new RowData(200, 200));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);


        for (int i = 0; i < 3; i++) {
            TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
            tblclmnNewColumn.setWidth(100);
            tblclmnNewColumn.setText("New Column");

        }

        for (int i = 0; i < 30; i++) {
            TableItem tableItem = new TableItem(table, SWT.NONE);
            tableItem.setText(new String[]{"0", "1", "2"});
        }

        table.pack();

    }

}
