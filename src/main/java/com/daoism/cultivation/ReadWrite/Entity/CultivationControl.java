package com.daoism.cultivation.ReadWrite.Entity;

/**
 * Implementation of the CultivationCapability interface, used to control cultivation
 * aspects about the player
 */
public class CultivationControl {

    public static class CultivationHandler implements CultivationCapability {

        private int cultivation; //The cultivation level
        private boolean canCultivate; //If the player can cultivate

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
    }

}

