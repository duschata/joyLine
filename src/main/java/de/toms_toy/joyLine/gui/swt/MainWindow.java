package de.toms_toy.joyLine.gui.swt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import de.toms_toy.joyLine.constant.ChordStructure;
import de.toms_toy.joyLine.constant.Note;
import de.toms_toy.joyLine.model.Chord;
import de.toms_toy.joyLine.model.MeasureComposite;

public class MainWindow
{

	protected Shell shell;
	private Table table;

	private MeasureComposite chords = new MeasureComposite();
	private TableItem[] tableItems = new TableItem[24];
	private String lastDirectory;
	private ArrayList<String[]> csv = new ArrayList<String[]>();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			MainWindow window = new MainWindow();
			window.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open()
	{
		chords.add(new Chord(Note.C, ChordStructure.Major7Arp));

		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	public void refreshTableItems()
	{
		table.removeAll();

		while (table.getColumnCount() > 0)
		{
			table.getColumns()[0].dispose();
		}

		csv.clear();

		TableColumn firstColumn = new TableColumn(table, SWT.NONE);
		firstColumn.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Logger.getLogger(getClass()).debug(e + " selected");
			}
		});
		firstColumn.setWidth(65);
		firstColumn.setText("Note");

		String[] header = new String[chords.size() + 1];
		header[0] = "Note";
		int j = 1;
		for (final Chord chord : chords)
		{

			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(50);

			tblclmnNewColumn_1.setText(chord.getRoot().getName() + System.lineSeparator() + chord.getChordStructure().getShortName());

			header[j++] = chord.getRoot().getName() + chord.getChordStructure().getShortName();

			//			tblclmnNewColumn_1.pack();

			//			Logger.getLogger(getClass()).debug("Column added");

			tblclmnNewColumn_1.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					Logger.getLogger(getClass()).debug(e + " selected");
					ChordChooser.main(chord, MainWindow.this);
					//					tblclmnNewColumn_1.setText(chord.getRoot().getName() + " " + chord.getChordStructure().getName());
					refreshTableItems();
				}
			});

		}

		csv.add(header);

		for (int i = tableItems.length - 1; i > -1; i--)
		{
			TableItem tableItem = new TableItem(table, SWT.NONE);

			ArrayList<String> chordNotes = new ArrayList<String>();
			chordNotes.add(Note.getChromaticNoteByDegree(i % 12).getName());

			for (Chord chord : chords)
			{
				chordNotes.add(chord.getIntervallNameByDegree(i % 12));
			}

			String[] chordNotesAsArray = chordNotes.toArray(new String[] {});
			tableItem.setText(chordNotesAsArray);
			csv.add(chordNotesAsArray);

		}

		table.pack();

		shell.pack();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents()
	{
		shell = new Shell();

		//		FontData defaultFont = new FontData("Musica", 10, SWT.NORMAL);

		shell.setImage(SWTResourceManager.getImage(MainWindow.class, "/notes.png"));
		shell.setSize(471, 698);
		shell.setText("Hennings Joy Lines");
		//		Composite composite = new Composite(shell, SWT.BORDER);
		//        composite.setLayout((new RowLayout(SWT.HORIZONTAL)));

		shell.setLayout((new RowLayout(SWT.HORIZONTAL)));

		//		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new RowData(200, 600));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		//		table.setFont(new Font(Display.getDefault(), defaultFont));

		shell.addListener(SWT.Resize, new Listener()
		{

			public void handleEvent(Event arg0)
			{
				table.setLayoutData(new RowData(shell.getSize().x - 80, shell.getSize().y - 80));
				//				refreshTableItems();

				Logger.getLogger(getClass()).debug("Shell resized...");

			}
		});

		refreshTableItems();

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("File");

		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);

		MenuItem openMenuItem = new MenuItem(menu_1, SWT.NONE);
		openMenuItem.setText("Open");
		openMenuItem.setImage(SWTResourceManager.getImage(MainWindow.class, "/open.png"));
		openMenuItem.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent selectionEvent)
			{

				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterNames(new String[] { "JoyLine", "All Files (*.*)" });
				dialog.setFilterExtensions(new String[] { "*.xjl", "*.*" });

				if (lastDirectory == null)
					dialog.setFilterPath(System.getProperty("user.home"));
				else
					dialog.setFilterPath(lastDirectory);

				dialog.open();

				if (dialog.getFilterPath() != null)
					lastDirectory = dialog.getFilterPath();

				Logger.getLogger(getClass()).debug("filter path: " + dialog.getFilterPath());
				Logger.getLogger(getClass()).debug("file name: " + dialog.getFileName());

				if (!dialog.getFileName().equals(""))
				{
					try
					{

						File file = new File(dialog.getFilterPath() + File.separator + dialog.getFileName());
						JAXBContext jaxbContext = JAXBContext.newInstance(MeasureComposite.class);

						Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
						chords = (MeasureComposite) jaxbUnmarshaller.unmarshal(file);
						System.out.println(chords);

						refreshTableItems();

					}
					catch (JAXBException e)
					{
						e.printStackTrace();
					}
				}

			}

		});

		MenuItem saveMenuItem = new MenuItem(menu_1, SWT.NONE);
		saveMenuItem.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Logger.getLogger(getClass()).debug("Save...");
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterNames(new String[] { "JoyLine", "All Files (*.*)" });
				dialog.setFilterExtensions(new String[] { "*.xjl", "*.*" });

				if (lastDirectory == null)
					dialog.setFilterPath(System.getProperty("user.home"));
				else
					dialog.setFilterPath(lastDirectory);

				dialog.open();

				if (dialog.getFilterPath() != null)
					lastDirectory = dialog.getFilterPath();

				Logger.getLogger(getClass()).debug("filter path: " + dialog.getFilterPath());
				Logger.getLogger(getClass()).debug("file name: " + dialog.getFileName());
				String fileName = dialog.getFileName();
				String[] fileNameParts = fileName.split("\\.");

				for (String test : fileNameParts)
				{
					Logger.getLogger(getClass()).debug(test);
				}

				String extension = ".xjl";

				if (fileNameParts[fileNameParts.length - 1].equals("xjl"))
					extension = "";

				File file = new File(dialog.getFilterPath() + File.separator + dialog.getFileName() + extension);
				StringWriter stringWriter = new StringWriter();

				JAXBContext jaxbContext;
				try
				{
					jaxbContext = JAXBContext.newInstance(MeasureComposite.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

					// output pretty printed
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

					jaxbMarshaller.marshal(chords, stringWriter);

					Logger.getLogger(getClass()).debug(stringWriter);

					jaxbMarshaller.marshal(chords, file);
				}
				catch (JAXBException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		saveMenuItem.setText("Save");
		saveMenuItem.setImage(SWTResourceManager.getImage(MainWindow.class, "/save.png"));

		MenuItem exportMenuItem = new MenuItem(menu_1, SWT.NONE);
		exportMenuItem.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Logger.getLogger(getClass()).debug("Export...");
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterNames(new String[] { "CSV", "All Files (*.*)" });
				dialog.setFilterExtensions(new String[] { "*.csv", "*.*" });

				if (lastDirectory == null)
					dialog.setFilterPath(System.getProperty("user.home"));
				else
					dialog.setFilterPath(lastDirectory);

				dialog.open();

				if (dialog.getFilterPath() != null)
					lastDirectory = dialog.getFilterPath();

				Logger.getLogger(getClass()).debug("filter path: " + dialog.getFilterPath());
				Logger.getLogger(getClass()).debug("file name: " + dialog.getFileName());
				String fileName = dialog.getFileName();
				String[] fileNameParts = fileName.split("\\.");

				for (String test : fileNameParts)
				{
					Logger.getLogger(getClass()).debug(test);
				}

				String extension = ".csv";

				if (fileNameParts[fileNameParts.length - 1].equals("csv"))
					extension = "";

				JAXBContext jaxbContext;
				FileWriter fw;
				try
				{
					fw = new FileWriter(dialog.getFilterPath() + File.separator + dialog.getFileName() + extension);
					StringWriter sw = new StringWriter();
					for (String[] rows : csv)
					{
						String separator = "";
						for (String cell : rows)
						{
							sw.write(separator + "\"" + cell + "\"");
							separator = ", ";
						}
						sw.write(System.lineSeparator());
					}

					fw.write(sw.toString());
					fw.close();
				}
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		exportMenuItem.setText("Export");
		exportMenuItem.setImage(SWTResourceManager.getImage(MainWindow.class, "/csv.png"));

		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Logger.getLogger(getClass()).debug("close...");

				chords.clear();

				chords.add(new Chord(Note.C, ChordStructure.Major7Arp));
				refreshTableItems();

			}
		});
		mntmNewItem_1.setText("Close");
		mntmNewItem_1.setImage(SWTResourceManager.getImage(MainWindow.class, "/close.png"));

	}

	public Table getTable()
	{
		return table;
	}

	public void setTable(Table table)
	{
		this.table = table;
	}

	public MeasureComposite getChords()
	{
		return chords;
	}

}
