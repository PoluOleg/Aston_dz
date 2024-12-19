package org.example;

import java.util.Comparator;

/**
 * Класс - кастомная реализация ArrayList
 * @param <E> некоторый класс
 */
public class ArrayList_ArtyomGordich<E> implements IntensiveList<E> {
    /** Поле массив объектов */
    private E[] mas;
    /** Поле флажок сортировки; принимает True, при вызове метода quickSort, принимает False по-умолчанию
     * или при вызове методов изменяющих/добавляющих/удаляющих элементы массива */
    private boolean isSorted = false;

    /**
     * Конструктор объекта с пустым массивом заданного размера
     * @param size размер массива
     */
    ArrayList_ArtyomGordich(int size) {
        mas = (E[]) new Object[size];
    }

    /**
     * Конструктор объекта, создающий массив из передаваемых элементов
     * @param elements набор объектов для сохранения
     */
    ArrayList_ArtyomGordich(E ... elements){
        mas = elements;
    }

    /**
     * Метод для получения размера списка
     * @return возвращает размер списка
     */
    public int size() {
        return mas.length;
    }

    /**
     * Метод для добавления элемента в конец
     * @param element элемент для добавления
     */
    public void add(E element) {
        try {
            E[] bufmas = (E[]) new Object[size() + 1];
            if (size() - 1 >= 0) System.arraycopy(mas, 0, bufmas, 0, size() - 1);
            bufmas[bufmas.length - 1] = element;
            mas = bufmas;
            isSorted = false;
    } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Индекс выходит за переделы массива");
    }
    }

    /**
     * Метод для добавления элемента в заданную позицию
     * @param index индекс позиции для вставки
     * @param element объект, который будет вставлен
     */
    public void add(int index, E element) {
        try {
            E[] bufmas = (E[]) new Object[size() + 1];
            System.arraycopy(mas, 0, bufmas, 0, index);
            bufmas[index] = element;
            System.arraycopy(mas, index + 1, bufmas, index + 1, bufmas.length - (index + 1));
            mas = bufmas;
            isSorted = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Индекс выходит за переделы массива");
        }
    }

    /**
     * Метод для получения элемента из заданной позиции
     * @param index позиция в массиве
     * @return возвращает объект в искомой позиции в случае успеха, null в случае выхода за пределы
     */
    public E get(int index) {
        try {
            return mas[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Индекс выходит за переделы массива");
            return null;
        }
    }

    /**
     * Метод для установки значения элемента в заданной позиции
     * @param index позиция в массиве
     * @param element объект, заменяющий элемент в заданной позиции
     * @return возвращает вставленный объект в случае успеха, null в случае неуспеха
     */
    public E set(int index, E element) {
        try {
            mas[index] = element;
            isSorted = false;
            return mas[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Индекс выходит за переделы массива");
            return null;
        }
    }

    /**
     * Метод для удаления элемента в заданной позиции
     * @param index позиция в массиве
     * @return возвращает удаленный объект в случае успеха, null в случае неуспеха
     */
    public E remove(int index) {
        try {
            E[] bufmas = (E[]) new Object[size() - 1];
            E deletedElement = mas[index];
            System.arraycopy(mas, 0, bufmas, 0, index);
            System.arraycopy(mas, index + 1, bufmas, index + 1, bufmas.length - (index + 1));
        return deletedElement;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Индекс выходит за переделы массива");
            return null;
        }
    }

    /**
     * Метод для очистки списка
     */
    public void clear() {
        mas = (E[]) new Object[0];
    }

    /**
     * Метод для сортировки списка быстрой сортировкой
     * @param comparator компаратор для сравнения объектов
     */
    public void quickSort(Comparator<E> comparator) {
        sort(comparator, 0, mas.length-1);
        isSorted = true;
    }

    private void sort(Comparator<E> comparator, int low, int high){
        while(low < high) {
            int pivotind = part(comparator, low, high);

            sort(comparator, low, pivotind-1);
            sort(comparator, pivotind+1, high);
        }
    }

    private int part(Comparator<E> comparator, int low, int high){
        int mid = low + (high - low)/2;
        E pivot = mas[mid];

        mas[mid] = mas[high];
        mas[high] = pivot;

        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(mas[j],pivot)<1) {
                i++;

                E buf = mas[i];
                mas[i] = mas[j];
                mas[j] = buf;
            }
        }

        E buf = mas[i + 1];
        mas[i + 1] = mas[high];
        mas[high] = buf;
        return i+1;
    }

    /**
     * Метод для получения флажка сортировки
     * @return возвращает флажок сортировки
     */
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Метод для урезания массива до заданного размера
     * @param newSize новый размер массива
     */
    public void split(int newSize){
        if (newSize < mas.length) {
            E[] bufmas = (E[]) new Object[newSize];
            int i = 0;
            while (i < newSize) {
                bufmas[i] = mas[i];
                i++;
            }
            mas = bufmas;
        } else {
            System.out.println("Размер нового листа равен или превосходит размер старого");
        }
    }
}
