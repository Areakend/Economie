package com.company;

public class Main {

	public static void main(String[] args) {
		final int nb_marche = 2;
		final int nb_agent = 10;
		final int nb_cycle = 20;

		int i;
		int j;
		// Instanciation des marches
		Environment[] marche = new Environment[nb_marche];
		for (i = 0; i < nb_marche; i++) {
			marche[i] = new Environment();
		}

		// Instanciation des agents
		Agent[] agent = new Agent[nb_agent];
		for (i = 0; i < nb_agent; i++) {
			agent[i] = new Agent();
		}

		// Iteration
		for (i = 2; i < nb_cycle; i++) {
			for (j = 0; j < nb_marche; j++) {
				marche[j].update(agent, j, nb_agent, i);
			}
			for (j = 0; j < nb_agent; j++) {
				agent[j].update(marche[agent[j].getMarche()-1], i);
			}
		}
	}
}
