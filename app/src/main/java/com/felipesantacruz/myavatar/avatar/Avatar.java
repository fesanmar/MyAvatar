package com.felipesantacruz.myavatar.avatar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Avatar {

    private final String name;
    private final String gender;
    private final Race race;
    private final Profession profession;

    private Avatar(String name, String gender, Race race, Profession profession) {
        this.name = name;
        this.gender = gender;
        this.race = race;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return String.format("%s_%s_%s",
                race.getName(), gender, profession.getName());
    }

    public static class Builder implements AvatarBuilderPublisher {
        private String name;
        private String gender;
        private Race race;
        private Profession profession;
        private Collection<AvatarBuilderClient> subscribers = new HashSet<>();
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
