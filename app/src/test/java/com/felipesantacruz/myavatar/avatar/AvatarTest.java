package com.felipesantacruz.myavatar.avatar;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AvatarTest {

    private static final String ELF = "elf";
    private static final String RANGER = "ranger";
    private static final String ELF_NAME = "Legolas";
    private final Race elf = mock(Race.class);
    private final Profession ranger = mock(Profession.class);

    @Before
    public void setUp() {
        when(elf.getName()).thenReturn(ELF);
        when(ranger.getName()).thenReturn(RANGER);
    }

    @Test
    public void maleAvatarGetsImageName() {
        Avatar avatar = new Avatar.Builder()
                .withName(ELF_NAME)
                .withRace(elf)
                .male()
                .withProfession(ranger)
                .build();
        assertThat(avatar.getImageName()).isEqualTo("elf_male_ranger");
    }

    @Test
    public void femaleAvatarGetsImageName() {
        Avatar avatar = new Avatar.Builder()
                .withName(ELF_NAME)
                .withRace(elf)
                .female()
                .withProfession(ranger)
                .build();
        assertThat(avatar.getImageName()).isEqualTo("elf_female_ranger");
    }

}
