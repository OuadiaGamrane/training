## Formation Spring : 

* Tp #3: 

- Ajouter Une table d'audit :

```
public class Audit {
 private Date date;
 private EventType eventType;
 private String message;
```

* Ajout la logic d'audit au virement