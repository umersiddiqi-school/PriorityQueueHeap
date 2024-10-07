package org.example;

public class PriorityQueueHeap implements PriorityQueue {
    int length;
    House[] houses;

    public PriorityQueueHeap(){
        length = 0;
        houses = new House[1000];
    }

    public PriorityQueueHeap(PriorityQueueHeap other){
        if(other.isEmpty()){
            length = 0;
            houses = new House[1000];
        }
        else{
            length = 0;
            houses = new House[other.getLength()];
            House temp = other.getRoot();
            while(temp != null){
                this.add(temp.deepCopy());
                temp = this.getMostExpensive();
            }
        }
    }

    public PriorityQueueHeap deepCopy(){
        return new PriorityQueueHeap(this);
    }

    @Override
    public void add(House a) {
        if(length == 0){
            houses[0] = a;
            length++;
        }
        else{
            houses[length] = a;
            heapifyUp(length);
            length++;
        }
    }

    private void heapifyUp(int index){
        int tempIndex = index;
        if(index > length){
            return;
        }
        while (houses[tempIndex].getValue() > houses[getParentIndex(tempIndex)].getValue()) {
            swap(tempIndex, getParentIndex(tempIndex));
            tempIndex = getParentIndex(tempIndex);
            if(tempIndex == 0){
                return;
            }
        }
    }
    //current issue: house in leftchildindex/rightchildindex pointing to null so .getvalue gives nullpointerexception
    private void heapifyDown(int index){
        if(!hasChild(index) || index > length){
            return;
        }
        int childIndex = 0;
        if(hasChild(index)){
            childIndex = getGreatestChildIndex(index);
        }
        if(houses[index].getValue() < houses[childIndex].getValue()){
            swap(index, childIndex);
            heapifyDown(childIndex);
        }
        /*int tempIndex = 0;
        int childIndex = 0;
        if (hasChild(tempIndex)){
            childIndex = getGreatestChildIndex(tempIndex);
        }
        while((tempIndex < length-1) && hasChild(tempIndex)  && (houses[tempIndex].getValue() < houses[childIndex].getValue())){
            try{
                swap(tempIndex, childIndex);
                if(hasChild(childIndex)){
                    tempIndex = childIndex;
                    childIndex = getGreatestChildIndex(childIndex);
                } else{
                    break;
                }
            }catch(ArrayIndexOutOfBoundsException e){
                break;
            }
        }*/
    }

    @Override
    public House getMostExpensive() {
        House maxVal = getRoot();
        if(length > 0){
            houses[0] = houses[length-1];
            length--;
            heapifyDown(0);
        }
        else{
            length--;
            return maxVal;
        }
        return maxVal;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    private int getParentIndex(int index){
        return (index - 1)/2;
    }

    private int getLeftChildIndex(int index){
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index){
        return 2 * index + 2;
    }

    private void swap(int index1, int index2){
        House temp = houses[index1];
        houses[index1] = houses[index2];
        houses[index2] = temp;
    }

    private House getRoot(){
        return houses[0];
    }

    private boolean hasChild(int index){
        return !(index >= (length / 2) && index <= length);
    }

    private int getGreatestChildIndex(int index){
        return houses[getLeftChildIndex(index)].getValue() > houses[getRightChildIndex(index)].getValue() ? getLeftChildIndex(index): getRightChildIndex(index);
    }


}
