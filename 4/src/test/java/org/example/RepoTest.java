package org.example;

import java.util.Optional;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class RepoTest {
    private Repo repository;

    @BeforeEach
    void setUp() {
        repository = new Repo();
    }

    @Test
    void testSaveNewMage() {
        repository.save(new Mage("test", 0));
        Optional<Mage> foundEntity = repository.find("test");
        assertThat(foundEntity).isPresent().matches(mage -> mage.get().getName().equals("test"));
    }

    @Test
    void testSaveExistingMage() {
        repository.save(new Mage("test", 0));
        assertThatThrownBy(() -> repository.save(new Mage("test", 0))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testDeleteExistingMage() {
        Mage entity = new Mage("test", 0);
        repository.save(entity);
        repository.delete("test");
        assertThat(repository.find("test")).isEmpty();
    }

    @Test
    void testDeleteNonExistingMage() {
        assertThatThrownBy(() -> repository.delete("test")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testFindExistingMage() {
        Mage mage = new Mage("test", 0);
        repository.save(mage);
        Optional<Mage> foundEntity = repository.find("test");
        assertThat(foundEntity).isPresent().contains(mage);
    }

    @Test
    void testFindNonExistingMage() {
        Optional<Mage> foundMage = repository.find("test");
        assertThat(foundMage).isEmpty();
    }
}
