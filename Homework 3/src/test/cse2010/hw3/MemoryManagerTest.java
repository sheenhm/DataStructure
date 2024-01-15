package cse2010.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Vector;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemoryManagerTest {

    private MemoryManager manager;
    Vector<Block> blocks;
    Random generator;

    @BeforeEach
    public void setup() {
        manager = new MemoryManager(1024);
        blocks = new Vector<>();
        generator = new Random(System.currentTimeMillis());
    }

    @Test
    public void should_allocate_memory_if_request_size_is_less_than_available_heap() {
        Block block = manager.malloc(256);

        assertEquals(256, block.size);
        assertEquals(0, block.start);
        assertEquals(255, block.end);
        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(768, manager.getTotalFreeSize());
    }

    @Test
    public void should_allocate_memory_if_successive_requests_can_be_granted() {
        manager.malloc(256);
        manager.malloc(64);
        manager.malloc(128);
        manager.malloc(512);

        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(64, manager.getTotalFreeSize());
    }

    @Test
    public void sum_of_allocated_and_available_memory_should_be_equal_to_capacity() {
        manager.malloc(256);
        manager.malloc(64);
        manager.malloc(128);
        manager.malloc(512);

        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(64, manager.getTotalFreeSize());
    }

    @Test
    public void should_combine_with_upper_adjacent_free_block() {
        Block b1 = manager.malloc(256);
        Block b2 = manager.malloc(256);
        Block b3 = manager.malloc(256);
        Block b4 = manager.malloc(256);
        manager.free(b4);
        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(256, manager.getTotalFreeSize());
        manager.free(b3);
        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(512, manager.getTotalFreeSize());
    }

    @Test
    public void should_combine_with_lower_adjacent_free_block() throws Exception {
        Block b1 = manager.malloc(256);
        Block b2 = manager.malloc(256);
        Block b3 = manager.malloc(256);
        Block b4 = manager.malloc(256);
        manager.free(b3);
        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(256, manager.getTotalFreeSize());
        manager.free(b4);
        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(512, manager.getTotalFreeSize());
    }

    @Test
    public void should_combine_both_adjacent_free_blocks() throws Exception {
        Block b1 = manager.malloc(256);
        Block b2 = manager.malloc(256);
        Block b3 = manager.malloc(256);
        Block b4 = manager.malloc(256);
        manager.free(b2);
        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(256, manager.getTotalFreeSize());
        manager.free(b4);
        assertEquals(2, manager.getFreeBlockCount());
        assertEquals(512, manager.getTotalFreeSize());
        manager.free(b3);
        assertEquals(1, manager.getFreeBlockCount());
        assertEquals(768, manager.getTotalFreeSize());
    }

    @Test
    public void should_throw_exception_if_request_is_bigger_than_any_free_blocks() {
        assertThrows(OutOfMemoryException.class, () -> manager.malloc(2048));
    }

    @Test
    public void should_throw_exception_if_out_of_memory_due_to_fragmentation() {
        manager.malloc(256);
        Block b1 = manager.malloc(64);
        manager.malloc(256);
        Block b2 = manager.malloc(64);
        manager.malloc(384);
        manager.free(b1);
        manager.free(b2);

        assertThrows(OutOfMemoryException.class, () -> manager.malloc(128));
    }

    @Test
    public void random_test() {
        for (int n = 0; n < 100; n++) {
            manager = new MemoryManager(1024);
            blocks = new Vector<>();

            for (int i = 0; i < 100; i++) {
                int operation = generator.nextInt() % 2;
                if (operation == 0) { // alloc
                    int size = genRequest();
                    if (size > 0) {
                        try {
                            blocks.add(manager.malloc(size));
                            assertEquals(1024, manager.getTotalFreeSize() + allocatedMemory());
                        } catch (OutOfMemoryException e) {
                            /* ignore this because of fragmentation */
                            assertEquals(1024, manager.getTotalFreeSize() + allocatedMemory());
                        }
                    }
                } else {
                    int index = pickReturnBlock();
                    if (index != -1) {
                        manager.free(blocks.get(index));
                        blocks.remove(index);
                        assertEquals(1024, manager.getTotalFreeSize() + allocatedMemory());
                    }
                }
            }
        }
    }

    private int pickReturnBlock() {
        if (blocks.size() == 0) return -1;
        else
            return abs(generator.nextInt()) % blocks.size();
    }

    private int genRequest() {
        int size = 0;
        int gen = abs(generator.nextInt()) % 5;
        switch (gen) {
            case 0:
                size = 16;
                break;
            case 1:
                size = 32;
                break;
            case 2:
                size = 64;
                break;
            case 3:
                size = 128;
                break;
            case 4:
                size = 256;
                break;
        }
        if (size <= manager.getTotalFreeSize()) {
            return size;
        } else
            return 0;
    }

    private int allocatedMemory() {
        return blocks.stream().mapToInt(b -> b.size).sum();
    }

}