package org.example;

import java.util.Optional;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControllerTest {
    private Repo repository;
    private Controller controller;

    @BeforeEach
    void setUp() {
        repository = mock(Repo.class);
        controller = new Controller(repository);
    }

    @Test
    void testSaveNewMage() {
        controller.save("test", "0");
        verify(repository, times(1)).save(any(Mage.class));
    }

    @Test
    void testSaveExistingMage() {
        doThrow(IllegalArgumentException.class).when(repository).save(any(Mage.class));
        assertThat(controller.save("test", "0")).isEqualTo("bad request");
    }

    @Test
    void testDeleteExistingMage() {
        assertThat(controller.delete("test")).isEqualTo("done");
        verify(repository, times(1)).delete("test");
    }

    @Test
    void testDeleteNonExistingMage() {
        controller.delete("test");
        verify(repository, times(1)).delete("test");
    }

    @Test
    void testFindExistingMage() {
        when(repository.find("test")).thenReturn(Optional.of(new Mage("test", 0)));
        assertThat(controller.find("test")).isEqualTo("Mage { Name: test, Level: 0 }");
    }

    @Test
    void testFindNonExistingMage() {
        when(repository.find("test")).thenReturn(Optional.empty());
        assertThat(controller.find("test")).isEqualTo("not found");
    }

    @Test
    void testControllerPrinting(){
        Repo repository = new Repo();
        Controller controller = new Controller(repository);

        System.out.println(controller.save("test","0"));
        System.out.println(controller.save("test","0"));
        System.out.println(controller.find("test"));
        System.out.println(controller.find("test2"));
        System.out.println(controller.delete("test"));
        System.out.println(controller.delete("test2"));
    }
}
