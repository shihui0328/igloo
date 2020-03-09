package eu.telecomsudparis.csc4102.gestionclefshotel;

/**
 * Classe représentant un client occupant une chambre au travers d'un badge
 * pouvant en déverrouiller la porte. Les associations intermédiaires sont à
 * sens double.
 * 
 * @see    Chambre
 * @see    Badge
 * @see    GestionClefsHotel
 * @author Paul Mabileau
 */
public class Client {
	/**
	 * L'identifiant unique du client.
	 */
	private final long id;
	/**
	 * Le nom de famille du client.
	 */
	private final String nom;
	/**
	 * Le prénom du client.
	 */
	private final String prenom;
	/**
	 * Le badge potentiellement associé au client.
	 */
	private Badge badge;
	
	/**
	 * Construit le client en enregistrant les paramètres fournis.
	 * 
	 * @param id       L'identifiant unique du client.
	 * @param nom      Le nom de famille du client.
	 * @param prenomLe prénom du client.
	 */
	public Client(final long id, final String nom, final String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		
		assert this.invariant();
	}
	
	public boolean invariant() {
		return this.nom != null && !this.nom.equals("")
				&& this.prenom != null && !this.prenom.equals("");
	}
	
	/**
	 * @return L'identifiant du client.
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * @return Le nom de famille du client.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * @return Le prénom du client.
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * @return Le badge auquel est potentiellement associé le client.
	 */
	public Badge getBadge() {
		return this.badge;
	}
	
	/**
	 * Associe de manière unidirectionnelle le badge au client, c'est-à-dire
	 * sans enregistrer le client dans le badge.
	 * 
	 * @param badge Le badge à associer.
	 * @see         #associerBadge(Badge, boolean)
	 */
	public void associerBadge(final Badge badge) {
		this.associerBadge(badge, false);
	}
	
	/**
	 * Associe le badge donné au client et dans l'autre sens aussi si
	 * {@code bidirectionnel} vaut {@code true}.
	 * 
	 * @param badge          Le badge à associer.
	 * @param bidirectionnel S'il faut aussi faire enregistrer le client par le
	 *                       badge.
	 * @see                  #associerBadge(Badge)
	 */
	public void associerBadge(final Badge badge, boolean bidirectionnel) {
		this.badge = badge;
		
		if (bidirectionnel) {
			this.badge.associerClient(this, false);
		}
	}
	
	/**
	 * Rompt le lien d'association du client vers son badge sans en faire de
	 * même pour la direction opposée.
	 * 
	 * @see #dissocierBadge(boolean)
	 */
	public void dissocierBadge() {
		this.dissocierBadge(false);
	}
	
	/**
	 * Rompt le lien d'association du client vers son badge en en faisant de
	 * même pour la direction opposée si {@code bidirectionnel} vaut
	 * {@code true}.
	 * 
	 * @param bidirectionnel S'il faut aussi dissocier dans l'autre sens.
	 * @see                  #dissocierBadge()
	 */
	public void dissocierBadge(boolean bidirectionnel) {
		if (bidirectionnel && this.badge != null) {
			this.badge.dissocierClient(false);
		}
		
		this.badge = null;
	}
	
	/**
	 * Implémentation de hashCode() pour {@link Client} basée sur les membres
	 * {@link #id}, {@link #nom} et {@link #prenom}. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		result = prime * result + (this.nom == null ? 0 : this.nom.hashCode());
		result = prime * result + (this.prenom == null ? 0 : this.prenom.hashCode());
		return result;
	}
	
	/**
	 * Implémentation de equals() pour {@link Client} pour laquelle l'égalité
	 * est basée sur les membres {@link #id}, {@link #nom} et {@link #prenom}.
	 * <br>
	 * <br>
	 * {@inheritDoc}
	 * 
	 * @return Si le client est égal à l'objet donné.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Client)) {
			return false;
		}
		
		final Client other = (Client) obj;
		
		if (this.id != other.id) {
			return false;
		}
		
		if (this.nom == null) {
			if (other.nom != null) {
				return false;
			}
		}
		else if (!this.nom.equals(other.nom)) {
			return false;
		}
		
		if (this.prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		}
		else if (!this.prenom.equals(other.prenom)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Implémentation de toString() pour {@link Client}. <br>
	 * <br>
	 * {@inheritDoc}
	 * 
	 * @return Une représentation textuelle du client.
	 */
	@Override
	public String toString() {
		return String.format("Client [id = %s, nom = %s, prenom = %s]",
							this.id, this.nom, this.prenom);
	}
}