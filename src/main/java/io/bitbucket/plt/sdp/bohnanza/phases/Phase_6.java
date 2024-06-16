package io.bitbucket.plt.sdp.bohnanza.phases;

import io.bitbucket.plt.sdp.bohnanza.components.Player;
import io.bitbucket.plt.sdp.bohnanza.gui.Label;

public class Phase_6 implements Phase {
    @Override
    public void finishPhase(Player player) {

    }

    @Override
    public void updateLabel(Label label) {
        label.updateLabel("Draw 2 new bean-cards");
    }
}
