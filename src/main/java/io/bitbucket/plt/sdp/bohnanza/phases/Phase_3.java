package io.bitbucket.plt.sdp.bohnanza.phases;

import io.bitbucket.plt.sdp.bohnanza.components.Player;
import io.bitbucket.plt.sdp.bohnanza.gui.Label;

//Must take top two cards from deck and place in trading area
//Trading goes on until active player decides it’s over
public class Phase_3 implements Phase{

    @Override
    public void finishPhase(Player player) {

    }

    @Override
    public void updateLabel(Label label) {
        label.updateLabel("Plant beans from hand");
    }
}
