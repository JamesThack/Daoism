package com.daoism.cultivation.EntityData;

public class CultivationControl {




    public static class CultivationHandler implements CultivationCapability {

        private int cultivation;
        private boolean canCultivate;

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
    }




    }

