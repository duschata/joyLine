package de.toms_toy.joyLine.test;

import static de.toms_toy.joyLine.constant.Note.*;
import static de.toms_toy.joyLine.constant.ChordStructure.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import de.toms_toy.joyLine.constant.Note;
import de.toms_toy.joyLine.model.Chord;

public class ChordBuilderTest
{
//	@Test
	public void getDiatonicChord()
	{
		Note root = G;
		int rootOrdinal = root.ordinal();
		Note third = Note.getDiatonicNoteByDegree(rootOrdinal + 2);
		Note fifth = Note.getDiatonicNoteByDegree(rootOrdinal + 4);
		Note seventh = Note.getDiatonicNoteByDegree(rootOrdinal + 6);
		Note ninth = Note.getDiatonicNoteByDegree(rootOrdinal + 8);

		Logger.getLogger(getClass()).debug(root.toString() + ":" + third + ":" + fifth + ":" + seventh + ":" + ninth);
	}
	
	@Test
	public void getChromaticChord()
	{
		Chord chord = new Chord(C, Dominant7Full);
		
		Logger.getLogger(getClass()).debug(chord);
	}

}
