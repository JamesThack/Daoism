package com.daoism.cultivation.ReadWrite.Entity;

/**
 * Implementation of the CultivationCapability interface, used to control cultivation
 * aspects about the player
 */
public class CultivationControl {

    public static class CultivationHandler implements CultivationCapability {

        private int cultivation; //The cultivation level
        private boolean canCultivate; //If the player can cultivate
        private boolean isFly; //If the player is flying
        private String UUID; //The user ID

        @Override
        public void addCultivation(int cult) {
            this.cultivation += cult;
        }

        @Override
        public void setCultivate(boolean cult) {
            this.canCultivate = cult;
        }

        @Override
        public boolean canCultivate() {
            return this.canCultivate;
        }
        @Override
        public int getCultivationLevel() {
            return cultivation;
        }

        @Override
        public void setCultivationLevel(int cult) {
            this.cultivation = cult;
        }

        @Override
        public void setFlying(boolean flying) {
            this.isFly = flying;
        }

        @Override
        public boolean isFlying() {
            return this.isFly;
        }

        @Override
        public String getName() {
            return this.UUID;
        }

        @Override
        public void setName(String name) {
            this.UUID = name;
        }


    }

}

