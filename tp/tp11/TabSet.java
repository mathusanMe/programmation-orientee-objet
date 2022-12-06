import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class TabSet<E> implements Set<E> {
    private E[] tableau;

    public class TabIter implements Iterator<E> {
        private int index;

        public TabIter() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < tableau.length;
        }

        @Override
        public E next()  {
            return tableau[index++];
        }

        @Override
        public void remove() throws IllegalStateException {
            if (index == 0) {
                throw new IllegalStateException();
            } else {
                tableau[index - 1] = null;
            }
        }
    }

    public Iterator<E> iterator() {
        return new TabIter();
    }

    @SuppressWarnings("unchecked")
    public TabSet() {
        tableau =  (E[]) new Object[10];
    }

    public boolean contains(Object o) {
        for (E e : tableau) {
            if (e != null && e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        int size = 0;
        for (E e : this) {
            if (e != null) {
                size++;
            }
        }
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(E e) {
        if (e == null || contains(e)) {
            return false;
        }
        TabIter iterator = (TabIter) this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                tableau[iterator.index - 1] = e;
                return true;
            }
        }
        @SuppressWarnings("unchecked") E[] newTableau = (E[]) new Object[tableau.length * 2];
        for (int i = 0; i < tableau.length + 1; i++) {
            if (i == iterator.index) {
                newTableau[i] = e;
            } else if (i < tableau.length) {
                newTableau[i] = tableau[i];
            } else {
                newTableau[i] = null;
            }
        }
        tableau = newTableau;
        return false;
    }

    public boolean remove(Object o) {
        TabIter iterator = (TabIter) this.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();
            if (e != null && e.equals(o)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void clear() {
        TabIter iterator = (TabIter) this.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) {
            changed = add(e) || changed;
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            changed = remove(o) || changed;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (E e : this) {
            if (!c.contains(e)) {
                changed = remove(e) || changed;
            }
        }
        return changed;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for (E e : this) {
            if (e != null) {
                array[i++] = e;
            }
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            Class<?> c = a.getClass().getComponentType();
            @SuppressWarnings("unchecked") T[] array = (T[]) Array.newInstance(c, size());
            a = array;
        }
        int i = 0;
        for (E e : this) {
            if (e != null) {
                @SuppressWarnings("unchecked") T t = (T) e;
                a[i++] = t;
            }
        }
        return a;
    }
}
