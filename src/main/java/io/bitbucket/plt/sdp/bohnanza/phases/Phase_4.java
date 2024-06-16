package io.bitbucket.plt.sdp.bohnanza.phases;

import io.bitbucket.plt.sdp.bohnanza.components.Player;
import io.bitbucket.plt.sdp.bohnanza.gui.Label;

//Draw 3 cards from deck
public class Phase_4 implements Phase{

    @Override
    public void finishPhase(Player player) {

    }

    @Override
    public void updateLabel(Label label) {
        label.updateLabel("Reveal beans from draw pile");
    }
}
