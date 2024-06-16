package io.bitbucket.plt.sdp.bohnanza.phases;

import io.bitbucket.plt.sdp.bohnanza.components.Player;
import io.bitbucket.plt.sdp.bohnanza.gui.Label;

//Use beans from previous turn
public class Phase_1 implements Phase {

    @Override
    public void finishPhase(Player player) {

    }

    @Override
    public void updateLabel(Label label) {
        label.updateLabel("Use beans from previous turn");
    }


}
