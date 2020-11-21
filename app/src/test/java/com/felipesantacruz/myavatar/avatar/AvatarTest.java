package com.felipesantacruz.myavatar.avatar;

import com.felipesantacruz.myavatar.repeatabletest.RepeatTest;
import com.felipesantacruz.myavatar.repeatabletest.RepeatedTestRule;
import com.google.common.collect.Range;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.felipesantacruz.myavatar.avatar.Avatar.MAX_LIFE_POINTS;
import static com.felipesantacruz.myavatar.avatar.Avatar.MAX_MANA_POINTS;
import static com.felipesantacruz.myavatar.avatar.Avatar.MAX_SPEED_POINTS;
import static com.felipesantacruz.myavatar.avatar.Avatar.MAX_STRENGTH_POINTS;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AvatarTest {

    private static final String ELF = "elf";
    private static final String RANGER = "ranger";
    private static final String ELF_NAME = "Legolas";
    private static final int ZERO = 0;
    private final Race elf = mock(Race.class);
    private final Profession ranger = mock(Profession.class);

    @Rule
    public RepeatedTestRule repeatRule = new RepeatedTestRule();

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

    @Test
    @RepeatTest(times = 100)
    public void statsAreInTheRightRange() {
        Avatar avatar = new Avatar.Builder().build();
        assertThat(avatar.getLifePoints()).isIn(Range.closed(ZERO, MAX_LIFE_POINTS));
        assertThat(avatar.getManaPoints()).isIn(Range.closed(ZERO, MAX_MANA_POINTS));
        assertThat(avatar.getStrengthPoints()).isIn(Range.closed(ZERO, MAX_STRENGTH_POINTS));
        assertThat(avatar.getSpeedPoints()).isIn(Range.closed(ZERO, MAX_SPEED_POINTS));
    }

}
