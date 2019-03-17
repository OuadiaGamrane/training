## Formation Spring : 

* Tp #3: 

- Ajouter Une table d'audit :

```
public class Audit {
 private Date date;
 private EventType eventType; // VIREMENT, RECHARGE...
 private String message;
```

- Un service d'audit qui permet de persisté les events d'audit + recherche dans la table avec un type

* Ajout la logic d'audit au virement