FROM postgres:latest
ENV PGPORT 5433
COPY postgres-db-config/init-db /docker-entrypoint-initdb.d/init-db.sql
EXPOSE 5433
