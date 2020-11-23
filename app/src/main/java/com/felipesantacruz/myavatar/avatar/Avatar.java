package com.felipesantacruz.myavatar.avatar;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Avatar {
    public static final int MAX_LIFE_POINTS = 100;
    public static final int MAX_MANA_POINTS = 10;
    public static final int MAX_STRENGTH_POINTS = 20;
    public static final int MAX_SPEED_POINTS = 5;

    private final String name;
    private final String gender;
    private final Race race;
    private final Profession profession;
    private final Random random = new Random();
    private final int lifePoints;
    private final int manaPoints;
    private final int strengthPoints;
    private final int speedPoints;

    private Avatar(String name, String gender, Race race, Profession profession) {
        this.name = name;
        this.gender = gender;
        this.race = race;
        this.profession = profession;
        lifePoints = getRandomIntFromZeroTo(MAX_LIFE_POINTS);
        manaPoints = getRandomIntFromZeroTo(MAX_MANA_POINTS);
        strengthPoints = getRandomIntFromZeroTo(MAX_STRENGTH_POINTS);
        speedPoints = getRandomIntFromZeroTo(MAX_SPEED_POINTS);
    }

    public int getRandomIntFromZeroTo(int include) {
        return random.nextInt(include + 1);
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return String.format("%s_%s_%s",
                race.getName(), gender, profession.getName());
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public int getStrengthPoints() {
        return strengthPoints;
    }

    public int getSpeedPoints() {
        return speedPoints;
    }

    public static class Builder implements AvatarBuilderPublisher {
        private String name;
        private String gender;
        private Race race;
        private Profession profession;
        private final Collection<AvatarBuilderClient> subscribers = new HashSet<>();
        private Avatar avatar;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRace(Race race) {
            this.race = race;
            return this;
        }

        public Builder male() {
            this.gender = "male";
            return this;
        }

        public Builder female() {
            this.gender = "female";
            return this;
        }

        public Builder withProfession(Profession profession) {
            this.profession = profession;
            return this;
        }

        public Avatar build() {
            avatar = new Avatar(this.name, this.gender, this.race, this.profession);
            publish();
            return avatar;
        }

        @Override
        public void addSubscriber(AvatarBuilderClient client) {
            subscribers.add(client);
        }

        @Override
        public void unsubscribe(AvatarBuilderClient client) {
            subscribers.remove(client);
        }

        @Override
        public void publish() {
            for (AvatarBuilderClient subscriber : subscribers)
                subscriber.receive(avatar);
        }
    }
}
