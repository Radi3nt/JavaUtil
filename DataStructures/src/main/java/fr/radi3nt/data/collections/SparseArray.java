package fr.radi3nt.data.collections;

import java.util.Arrays;

public class SparseArray<T> {

    private static final Object DELETED = new Object();
    private static final int DEFAULT_CAPACITY = 10;
    private boolean garbage = false;

    private int[] keys;
    private Object[] values;
    private int size;

    public SparseArray() {
        this(DEFAULT_CAPACITY);
    }

    public SparseArray(int initialCapacity) {
        if (initialCapacity == 0) {
            keys = new int[0];
            values = new Object[0];
        } else {
            values = new Object[initialCapacity];
            keys = new int[values.length];
        }
        size = 0;
    }

    public SparseArray(SparseArray<? extends T> array) {
        this.keys = Arrays.copyOf(array.keys, array.keys.length);
        this.values = Arrays.copyOf(array.values, array.values.length);
        this.size = array.size;
    }

    public T get(int key) {
        return getOrDefault(key, null);
    }

    @SuppressWarnings("unchecked")
    public T getOrDefault(int key, T valueIfKeyNotFound) {
        int i = binarySearch(keys, size, key);
        if (i < 0 || values[i] == DELETED) {
            return valueIfKeyNotFound;
        } else {
            return (T) values[i];
        }
    }

    public void put(int key, T value) {
        int i = binarySearch(keys, size, key);
        if (i >= 0) {
            values[i] = value;
        } else {
            i = ~i;
            if (i < size && values[i] == DELETED) {
                keys[i] = key;
                values[i] = value;
                return;
            }
            if (garbage && size >= keys.length) {
                performGC();
                i = ~binarySearch(keys, size, key);
            }
            keys = insert(keys, size, i, key);
            values = insert(values, size, i, value);
            size++;
        }
    }

    public void remove(int key) {
        int i = binarySearch(keys, size, key);
        if (i >= 0) {
            if (values[i] != DELETED) {
                values[i] = DELETED;
                garbage = true;
            }
        }
    }

    public void removeAt(int index) {
        if (values[index] != DELETED) {
            values[index] = DELETED;
            garbage = true;
        }
    }

    public int keyAt(int index) {
        if (garbage) {
            performGC();
        }
        return keys[index];
    }

    @SuppressWarnings("unchecked")
    public T valueAt(int index) {
        if (garbage) {
            performGC();
        }
        return (T) values[index];
    }

    public void setValueAt(int index, T value) {
        if (garbage) {
            performGC();
        }
        values[index] = value;
    }

    public void clear() {
        int n = size;
        Object[] valArray = values;
        for (int i = 0; i < n; i++) {
            valArray[i] = null;
        }
        size = 0;
        garbage = false;
    }

    public int size() {
        if (garbage) {
            performGC();
        }
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Method to perform GC
     */
    private void performGC() {
        int n = size;
        int position = 0;
        int[] keyArray = keys;
        Object[] valArray = values;

        for (int i = 0; i < n; i++) {
            Object val = valArray[i];
            if (val != DELETED) {
                if (i != position) {
                    keyArray[position] = keyArray[i];
                    valArray[position] = val;
                    valArray[i] = null;
                }
                position++;
            }
            garbage = false;
            size = position;
        }

    }

    /**
     * Method to insert an element at a given index
     *
     * @param array
     * @param currentSize
     * @param index
     * @param element
     * @return {@link Object[]}
     */
    private Object[] insert(Object[] array, int currentSize, int index, T element) {
        assert currentSize <= array.length;
        if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        }
        Object[] newArray = new Object[currentSize * 2];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = element;
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        return newArray;
    }

    /**
     * Method to insert an integer at given index
     *
     * @param array
     * @param currentSize
     * @param index
     * @param element
     * @return {@link int[]}
     */
    private static int[] insert(int[] array, int currentSize, int index, int element) {
        assert currentSize <= array.length;
        if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        }
        int[] newArray = new int[currentSize * 2];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = element;
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        return newArray;
    }

    /**
     * Method to do a binary search on int array
     *
     * @param array
     * @param size
     * @param value
     * @return {@link int}
     */
    public static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return ~lo;
    }

}