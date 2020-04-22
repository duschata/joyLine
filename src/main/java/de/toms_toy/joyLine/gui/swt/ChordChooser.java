package de.toms_toy.joyLine.gui.swt;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import de.toms_toy.joyLine.constant.ChordStructure;
import de.toms_toy.joyLine.constant.Note;
import de.toms_toy.joyLine.model.Chord;
import de.toms_toy.joyLine.model.MeasureComposite;

public class ChordChooser extends Shell {

    private Chord chord;
    private MainWindow parentShell;

    public static void main(String[] args) {
        Chord chord = new Chord(Note.C, ChordStructure.Major7Full);
        main(chord, null);

    }

    public static void main(Chord chord, MainWindow parShell) {

        try {
            Display display = Display.getDefault();
            ChordChooser shell = new ChordChooser(display);
            shell.chord = chord;
            shell.parentShell = parShell;

            shell.createContents();

            shell.open();
            shell.layout();
            while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the shell.
     *
     * @param display
     */
    public ChordChooser(Display display) {
        super(display, SWT.SHELL_TRIM);
        setLayout(null);
//		createContents();

    }

    /**
     * Create contents of the shell.
     */
    protected void createContents() {
        setText("Akkord ausw\u00e4hlen");
        setImage(SWTResourceManager.getImage(this.getClass(), "/notes.png"));
        setSize(450, 300);

        final Chord chordTmp = new Chord(chord.getRoot(), chord.getChordStructure());

        final Combo combo = new Combo(this, SWT.READ_ONLY);
        combo.setBounds(35, 33, 183, 27);
        for (int i = 0; i < 12; i++) {
            combo.add(Note.getChromaticNoteByDegree(i % 12).getName(), i);
        }
        combo.select(chordTmp.getRoot().getChromaticDegree());

        combo.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                Logger.getLogger(getClass()).debug("modified");
                Logger.getLogger(getClass()).debug("index: " + combo.getSelectionIndex());
                chordTmp.setRoot(Note.getChromaticNoteByDegree(combo.getSelectionIndex()));

            }
        });

        final Combo combo_1 = new Combo(this, SWT.READ_ONLY);
        combo_1.setBounds(35, 92, 183, 27);

        for (ChordStructure chordStructure : ChordStructure.values()) {
            combo_1.add(chordStructure.getDisplayedName(), chordStructure.ordinal());
        }


        combo_1.select(chordTmp.getChordStructure().ordinal());
        combo_1.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent arg0) {
                chordTmp.setChordStructure(ChordStructure.values()[combo_1.getSelectionIndex()]);

            }
        });

        Button editButton = new Button(this, SWT.NONE);
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent e) {
                chord.setRoot(chordTmp.getRoot());
                chord.setChordStructure(chordTmp.getChordStructure());
                close();
            }
        });
        editButton.setBounds(35, 173, 76, 26);
        editButton.setText("\u00c4ndern");

        Button newButton = new Button(this, SWT.NONE);
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent e) {
                MeasureComposite chords = parentShell.getChords();
                int index = chords.indexOf(chord);
                chords.add(index + 1, chordTmp);
                close();
            }
        });
        newButton.setText("Neu");
        newButton.setBounds(142, 173, 76, 26);

    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }
}
