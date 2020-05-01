package de.toms_toy.joyLine.model;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "measureComposite", propOrder = {
        "chords"
})
public class MeasureComposite implements Collection<Chord> {

    @XmlElement(nillable = true)
    private List<Chord> chords = new ArrayList<Chord>();

    public int size() {
        return chords.size();
    }

    public boolean isEmpty() {
        return chords.isEmpty();
    }

    public boolean contains(Object o) {
        return chords.contains(o);
    }

    public Iterator<Chord> iterator() {
        return chords.iterator();
    }

    public Object[] toArray() {
        return chords.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return chords.toArray(a);
    }

    public boolean add(Chord e) {
        return chords.add(e);
    }

    public boolean remove(Object o) {
        return chords.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return chords.containsAll(c);
    }

    public boolean addAll(Collection<? extends Chord> c) {
        return chords.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Chord> c) {
        return chords.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return chords.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return chords.retainAll(c);
    }

    public void clear() {
        chords.clear();
    }

    public boolean equals(Object o) {
        return chords.equals(o);
    }

    public int hashCode() {
        return chords.hashCode();
    }

    public Chord get(int index) {
        return chords.get(index);
    }

    public Chord set(int index, Chord element) {
        return chords.set(index, element);
    }

    public void add(int index, Chord element) {
        chords.add(index, element);
    }

    public Chord remove(int index) {
        return chords.remove(index);
    }

    public int indexOf(Object o) {
        return chords.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return chords.lastIndexOf(o);
    }

    public ListIterator<Chord> listIterator() {
        return chords.listIterator();
    }

    public ListIterator<Chord> listIterator(int index) {
        return chords.listIterator(index);
    }

    public List<Chord> subList(int fromIndex, int toIndex) {
        return chords.subList(fromIndex, toIndex);
    }

    public List<Chord> getChords() {
        return chords;
    }

    public void setChords(List<Chord> chords) {
        this.chords = chords;
    }


}
