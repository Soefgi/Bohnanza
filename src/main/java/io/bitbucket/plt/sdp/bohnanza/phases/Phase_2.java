package io.bitbucket.plt.sdp.bohnanza.phases;

import io.bitbucket.plt.sdp.bohnanza.components.Player;
import io.bitbucket.plt.sdp.bohnanza.gui.Label;

//Give beans to the Bean-Mafia
public class Phase_2 implements Phase {

    @Override
    public void finishPhase(Player player) {

    }

    @Override
    public void updateLabel(Label label) {
        label.updateLabel("Give beans to the Bean-Mafia");
    }
}
