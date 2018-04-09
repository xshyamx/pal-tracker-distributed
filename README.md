

## Create the Services ##

``` sh
for svc in registration backlog timesheets allocations; do 
  cf create-service p-mysql p-mysql "tracker-${svc}-database"; 
done
```

## Update initials ##

``` sh
for svc in registration backlog timesheets allocations; do 
  sed -i.old 's/INITIALS/shyam/' manifest-${svc}.yml
done
```

## Update registration server ##

``` sh
for svc in backlog timesheets allocations; do 
  sed -i.old 's/FILL_ME_IN/registration-pal-shyam.apps.longs.pal.pivotal.io/' manifest-${svc}.yml
done
```
