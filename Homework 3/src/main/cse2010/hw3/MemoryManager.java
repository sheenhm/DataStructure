package cse2010.hw3;

/* Block will be used as a type argument */
class Block {
    public int size;
    public int start;
    public int end;

    /**
     * Constructs a block with the given size, start, and end.
     * @param size the size of the block
     * @param start the start index of the block
     * @param end the end index of the block
     */
    public Block(int size, int start, int end) {
        this.size = size;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + size +", " + start + ", " + end + ")";
    }
}

public class MemoryManager {
    private final DLinkedList<Block> heap = new DLinkedList<>();

    /**
     * Constructs a memory manager with the given capacity.
     * @param capacity the capacity of the memory manager
     */
    public MemoryManager(int capacity) {
        heap.addFirst(new Block(capacity, 0, capacity - 1));
    }

    /**
     * Allocates a block of memory with the given size.
     * @param size the size of the requested block
     * @return the allocated block
     * @throws OutOfMemoryException if there is no big-enough available block
     */
    public Block malloc(int size) throws OutOfMemoryException {
        Node<Block> current = heap.getFirst();
        Block block = current.getItem();

        while ((current != null) && (current != heap.getTrailer())) {
            if (block.size >= size) {
                if (block.size > size) {
                    int newStart = block.start + size;
                    Block newBlock = new Block(block.size - size, newStart, block.end);
                    block.size = size;
                    block.end = newStart - 1;
                    heap.addAfter(current, new Node<>(newBlock, null, null));
                }
                heap.remove(current);
                return block;
            }
            current = current.getNext();
        }

        throw new OutOfMemoryException("할당 가능한 블록이 없습니다.");
    }

    /**
     * Returns the given block to the memory manager.
     * @param block the block to free (i.e, to return to the memory manager)
     */
    public void free(Block block) {
        Node<Block> current = heap.getFirst();

        while (current.getNext() != null && current.getItem().end < block.start) {
            current = current.getNext();
        }

        heap.addBefore(current, new Node<>(block, null, null));
        mergeNeighborBlocks();
    }

    private void mergeNeighborBlocks() {
        Node<Block> current = heap.getFirst();

        while ((current != null) && (current.getNext() != heap.getTrailer())) {
            Block currentBlock = current.getItem();
            Block nextBlock = current.getNext().getItem();

            if (currentBlock.end + 1 == nextBlock.start || currentBlock.start - 1 == nextBlock.end) {
                Block mergedBlock = new Block(currentBlock.size + nextBlock.size, Math.min(currentBlock.start, nextBlock.start), Math.max(currentBlock.end, nextBlock.end));
                heap.addBefore(current, new Node<>(mergedBlock, null, null));
                heap.remove(current.getNext());
                heap.remove(current);
                current = heap.getFirst();
            } else {
                current = current.getNext();
            }
        }
    }

    /**
     * Returns the number of free blocks in the memory manager.
     * @return the number of free blocks in the memory manager
     */
    public int getFreeBlockCount() {
        return heap.getSize();
    }

    /**
     * Returns the total size of free blocks in the memory manager.
     * @return the total size of free blocks in the memory manager
     */
    public int getTotalFreeSize() {
        int totalFreeSize = 0;
        Node<Block> current = heap.getFirst();

        while ((current != null) && (current != heap.getTrailer())) {
            totalFreeSize += current.getItem().size;
            current = current.getNext();
        }

        return totalFreeSize;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}