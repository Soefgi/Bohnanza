package io.bitbucket.plt.sdp.bohnanza.phases;

import io.bitbucket.plt.sdp.bohnanza.components.Player;
import io.bitbucket.plt.sdp.bohnanza.gui.Label;

public interface Phase {

    void finishPhase(Player player);

    void updateLabel(Label label);
}
