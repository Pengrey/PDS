import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VectorGeneric<T> {
    private T[] vec;
    private int nElem;
    private final static int ALLOC = 50;
    private int dimVec = ALLOC; 
    
    @SuppressWarnings("unchecked")
    public VectorGeneric() {
        vec = (T[]) new Object[dimVec];
        nElem = 0;
    }
    
    public boolean addElem(T elem) {
        if(elem == null)
            return false;
        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }
    
    private void ensureSpace() {
        if( nElem >= dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem);
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for(int i = 0; i < nElem; i++) {
            if(vec[i].equals(elem)) {
                if(nElem-i-1 > 0) // not last element
                    System.arraycopy(vec, i+1, vec, i, nElem-i-1 );
                vec[--nElem] = null; // libertar último objecto para o GC
                return true;
            }
        }
        return false;
    }
    
    public int totalElem() {
        return nElem;
    }
    
    public T getElem(int i) {
        return(T) vec[i];
    }

    public Iterator<T> iterator() { 
        return(this).new VectorIterator<T>();
    }
    
    public ListIterator<T> listIterator() { 
        return(this).new listIterator<T>(0);
    }

    public ListIterator<T> listIterator(int indice) { 
        return(this).new listIterator<T>(indice);
    }

    private class VectorIterator<T> implements Iterator<T> {
        private int indice;

        VectorIterator() { 
            indice= 0; 
        }

        public boolean hasNext() {
            return(indice < nElem);
        }

        public T next() {
            if(hasNext()) 
                return (T)VectorGeneric.this.vec[indice++];
            throw new NoSuchElementException("only "+ nElem+ " elements");
        }
        
        public void remove() { // default since Java 8
            throw new UnsupportedOperationException("Operacao nao suportada!");
        }
    }

    private class listIterator<T> implements ListIterator<T> {
        private int indice;

        listIterator(int indice) { 
            this.indice = indice; 
        }

        public boolean hasNext() {
            if(this.indice < nElem){
                return true;
            }else{
                this.indice--;
                return false;
            }
        }

        public boolean hasPrevious() {
            if(this.indice >= 0){
                return true;
            }else{
                this.indice++;
                return false;
            }
        }

        public T next() {
            if(hasNext()) 
                return (T)VectorGeneric.this.vec[this.indice++];
            throw new NoSuchElementException("Index out of bounds");
        }

        public T previous() {
            if(hasPrevious()) 
                return (T)VectorGeneric.this.vec[this.indice--];
            throw new NoSuchElementException("Index out of bounds");
        }

        public int nextIndex() {
            return this.indice+1;
        }

        public int previousIndex() {
            return this.indice-1;
        }

        public void add(T arg0) {
            throw new UnsupportedOperationException("Operacao nao suportada!");
        }

        public void remove() {
            throw new UnsupportedOperationException("Operacao nao suportada!");
            
        }

        public void set(T arg0) {
            throw new UnsupportedOperationException("Operacao nao suportada!");
            
        }

    }
}