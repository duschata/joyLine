package de.toms_toy.joyLine.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import de.toms_toy.joyLine.constant.ChordStructure;
import de.toms_toy.joyLine.constant.Interval;
import de.toms_toy.joyLine.constant.Note;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chord", propOrder = { "chordStructure", "root", "chordNotesMap" })
public class Chord
{
	private Note root;
	private ChordStructure chordStructure;
	private Map<Integer, String> chordNotesMap = new HashMap<Integer, String>();

	public Chord()
	{}
	
	public Chord(Note root, ChordStructure chordStructure)
	{
		this.root = root;
		this.chordStructure = chordStructure;
		initChordNoteMap();
	}

	private void initChordNoteMap()
	{

		chordNotesMap.clear();
		int rootOffset = root.getChromaticDegree();

		for (Interval interval : chordStructure.getIntervals())
		{
			Note note = Note.getChromaticNoteByDegree(rootOffset + interval.getOffset());

			chordNotesMap.put(note.getChromaticDegree(), interval.getName());

		}
	}

	public String getIntervallNameByDegree(int degree)
	{
		String intervalName = chordNotesMap.get(degree);
		if (intervalName == null)
			return "";

		return intervalName;
	}

	@Override
	public String toString()
	{
		String stringRepresentation = "";

		int rootOffset = root.getChromaticDegree();

		for (Interval interval : chordStructure.getIntervals())
		{
			Note note = Note.getChromaticNoteByDegree(rootOffset + interval.getOffset());

			stringRepresentation += note.getName() + " [" + note.getChromaticDegree() + "](" + interval.getName() + ")";
		}

		return stringRepresentation;
	}

	public Note getRoot()
	{
		return root;
	}

	public void setRoot(Note root)
	{
		this.root = root;
		initChordNoteMap();
	}

	public ChordStructure getChordStructure()
	{
		return chordStructure;
	}

	public void setChordStructure(ChordStructure chordStructure)
	{
		this.chordStructure = chordStructure;
		initChordNoteMap();
	}

}
