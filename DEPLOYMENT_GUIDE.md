# AGT Facturacao Multi-Tenant - Multi-Platform Deployment Guide

Complete step-by-step instructions for running and deploying on Windows, macOS, and Linux.

---

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Local Development Setup](#local-development-setup)
   - [Windows](#windows)
   - [macOS](#macos)
   - [Linux](#linux)
3. [Running the Application](#running-the-application)
4. [Docker Deployment](#docker-deployment)
5. [Production Deployment](#production-deployment)
6. [Troubleshooting](#troubleshooting)

---

## Prerequisites

### Required Software (All Platforms)

- **Java Development Kit (JDK) 11+**
- **Maven 3.6+**
- **PostgreSQL 13+**
- **Docker & Docker Compose** (optional, for containerized deployment)
- **Git**

### Verify Installations

```bash
# Check Java
java -version

# Check Maven
mvn -version

# Check PostgreSQL
psql --version

# Check Docker (if using containers)
docker --version
docker-compose --version
```

---

## Local Development Setup

### Windows

#### Step 1: Install Java Development Kit (JDK)

1. Download JDK 11+ from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or use [OpenJDK](https://adoptopenjdk.net/)
2. Run the installer and follow the installation wizard
3. Set JAVA_HOME environment variable:
   - Right-click "This PC" → Properties → Advanced system settings → Environment Variables
   - Click "New" under System variables
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-11` (or your installation path)
4. Add Java to PATH:
   - In Environment Variables, find `Path` under System variables
   - Click "Edit" → "New"
   - Add: `%JAVA_HOME%\bin`

#### Step 2: Install Maven

1. Download Maven from [apache.org](https://maven.apache.org/download.cgi)
2. Extract to a folder (e.g., `C:\maven`)
3. Set MAVEN_HOME:
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\maven` (your extraction path)
4. Add Maven to PATH:
   - Add: `%MAVEN_HOME%\bin`
5. Verify:
   ```cmd
   mvn -version
   ```

#### Step 3: Install PostgreSQL

1. Download from [postgresql.org](https://www.postgresql.org/download/windows/)
2. Run installer and follow wizard
3. Remember the superuser password
4. Default port: 5432
5. Create a new database user:
   ```cmd
   psql -U postgres
   CREATE USER agt_user WITH PASSWORD 'your_secure_password';
   CREATE DATABASE agt_facturacao OWNER agt_user;
   \q
   ```

#### Step 4: Clone Repository

```cmd
cd C:\projects
git clone https://github.com/muanza/repomultilingua.git
cd repomultilingua
git checkout copilot/implementacao-melhorias-sistema-facturacao
```

#### Step 5: Configure Database

1. Navigate to the scripts directory:
   ```cmd
   cd scripts
   ```
2. Edit `setup-db.sh` for Windows environment or create `setup-db.bat`:
   ```batch
   @echo off
   REM Windows database setup
   set DB_USER=agt_user
   set DB_PASS=your_secure_password
   set DB_HOST=localhost
   set DB_PORT=5432
   set DB_NAME=agt_facturacao
   
   psql -U %DB_USER% -h %DB_HOST% -p %DB_PORT% -d %DB_NAME% -f schema.sql
   ```
3. Run setup:
   ```cmd
   setup-db.bat
   ```

#### Step 6: Build and Test

```cmd
cd ..\
mvn clean install
mvn test
```

---

### macOS

#### Step 1: Install Java Development Kit (JDK)

Using Homebrew (recommended):

```bash
# Install Homebrew if not already installed
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install JDK 11
brew install openjdk@11

# Set JAVA_HOME in shell profile (~/.zshrc for newer macOS, ~/.bash_profile for older)
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 11)' >> ~/.zshrc
source ~/.zshrc

# Verify
java -version
```

#### Step 2: Install Maven

```bash
# Using Homebrew
brew install maven

# Verify
mvn -version
```

#### Step 3: Install PostgreSQL

```bash
# Using Homebrew
brew install postgresql@13

# Start PostgreSQL service
brew services start postgresql@13

# Create database and user
createuser -P agt_user
createdb -O agt_user agt_facturacao

# Verify connection
psql -U agt_user -d agt_facturacao
\q
```

#### Step 4: Clone Repository

```bash
cd ~/projects
git clone https://github.com/muanza/repomultilingua.git
cd repomultilingua
git checkout copilot/implementacao-melhorias-sistema-facturacao
```

#### Step 5: Configure Database

```bash
# Navigate to scripts
cd scripts

# Make setup script executable
chmod +x setup-db.sh

# Edit setup script with your credentials (optional)
nano setup-db.sh

# Run setup
./setup-db.sh
```

#### Step 6: Build and Test

```bash
cd ../
mvn clean install
mvn test
```

---

### Linux (Ubuntu/Debian)

#### Step 1: Install Java Development Kit (JDK)

```bash
# Update package manager
sudo apt update

# Install JDK 11
sudo apt install openjdk-11-jdk

# Set JAVA_HOME
echo 'export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc

# Verify
java -version
```

#### Step 2: Install Maven

```bash
# Install Maven
sudo apt install maven

# Verify
mvn -version
```

#### Step 3: Install PostgreSQL

```bash
# Install PostgreSQL
sudo apt install postgresql postgresql-contrib

# Start PostgreSQL service
sudo systemctl start postgresql
sudo systemctl enable postgresql

# Create database and user
sudo -u postgres createuser -P agt_user
sudo -u postgres createdb -O agt_user agt_facturacao

# Verify
sudo -u postgres psql -d agt_facturacao
\q
```

#### Step 4: Clone Repository

```bash
cd ~/projects
git clone https://github.com/muanza/repomultilingua.git
cd repomultilingua
git checkout copilot/implementacao-melhorias-sistema-facturacao
```

#### Step 5: Configure Database

```bash
# Navigate to scripts
cd scripts

# Make setup script executable
chmod +x setup-db.sh

# Run setup (may need sudo for database scripts)
./setup-db.sh
```

#### Step 6: Build and Test

```bash
cd ../
mvn clean install
mvn test
```

---

## Running the Application

### Development Server (All Platforms)

#### Prerequisites
Ensure Docker and Docker Compose are installed and running.

#### Step 1: Start Infrastructure with Docker Compose

```bash
# Navigate to docker directory
cd docker

# Start containers in background
docker-compose -f docker-compose.yml up -d

# Verify containers are running
docker-compose -f docker-compose.yml ps

# View logs (if needed for debugging)
docker-compose -f docker-compose.yml logs -f
```

#### Step 2: Build All Modules

```bash
# Navigate to project root
cd ../

# Clean build
mvn clean install -DskipTests

# Build with tests (takes longer)
mvn clean install
```

#### Step 3: Run Individual Services

**Run Facturacao Core Service:**
```bash
mvn -pl facturacao-api spring-boot:run
# Access: http://localhost:8080
```

**Run POS Service:**
```bash
mvn -pl pos-api spring-boot:run
# Access: http://localhost:8081
```

**Run Stock Service:**
```bash
mvn -pl stock-api spring-boot:run
# Access: http://localhost:8082
```

**Run Users Service:**
```bash
mvn -pl users-api spring-boot:run
# Access: http://localhost:8083
```

**Run CRM Service:**
```bash
mvn -pl crm-api spring-boot:run
# Access: http://localhost:8084
```

#### Step 4: Run Tests

```bash
# Run all tests
mvn test

# Run tests for specific module
mvn -pl facturacao-api test

# Run tests with coverage report
mvn test jacoco:report
```

#### Step 5: Stop Infrastructure

```bash
# Stop and remove containers
docker-compose -f docker/docker-compose.yml down

# Stop containers without removing
docker-compose -f docker/docker-compose.yml stop
```

---

## Docker Deployment

### Container-Based Development and Staging

#### Step 1: Build Docker Images

```bash
# From project root, build all services
mvn clean package -DskipTests

# Navigate to docker directory
cd docker

# Build images (ensure Dockerfile exists for each service)
docker-compose -f docker-compose.yml build
```

#### Step 2: Run Full Stack with Docker Compose

```bash
# Start all services
docker-compose -f docker-compose.yml up -d

# Check service status
docker-compose -f docker-compose.yml ps

# View logs for specific service
docker-compose -f docker-compose.yml logs -f facturacao-api

# View all logs
docker-compose -f docker-compose.yml logs -f
```

#### Step 3: Access Services

| Service | URL | Port |
|---------|-----|------|
| Facturacao API | `http://localhost:8080` | 8080 |
| POS API | `http://localhost:8081` | 8081 |
| Stock API | `http://localhost:8082` | 8082 |
| Users API | `http://localhost:8083` | 8083 |
| CRM API | `http://localhost:8084` | 8084 |
| PostgreSQL | `localhost:5432` | 5432 |

#### Step 4: Health Checks

```bash
# Check Facturacao API health
curl http://localhost:8080/actuator/health

# Check all service health
for port in 8080 8081 8082 8083 8084; do
  echo "Port $port:"
  curl http://localhost:$port/actuator/health
done
```

#### Step 5: Cleanup

```bash
# Stop and remove all containers
docker-compose -f docker-compose.yml down

# Remove all images
docker-compose -f docker-compose.yml down --rmi all

# Remove volumes (careful: deletes data!)
docker-compose -f docker-compose.yml down -v
```

---

## Production Deployment

### Kubernetes Deployment (Recommended for Production)

#### Step 1: Build and Push Docker Images to Registry

```bash
# Build all services
mvn clean package -DskipTests

# Tag images for registry
docker tag agt-facturacao:latest your-registry/agt-facturacao:1.0.0
docker tag agt-pos:latest your-registry/agt-pos:1.0.0
docker tag agt-stock:latest your-registry/agt-stock:1.0.0
docker tag agt-users:latest your-registry/agt-users:1.0.0
docker tag agt-crm:latest your-registry/agt-crm:1.0.0

# Push to registry
docker push your-registry/agt-facturacao:1.0.0
docker push your-registry/agt-pos:1.0.0
docker push your-registry/agt-stock:1.0.0
docker push your-registry/agt-users:1.0.0
docker push your-registry/agt-crm:1.0.0
```

#### Step 2: Create Kubernetes Manifests

Create `k8s/` directory with YAML files:

**k8s/namespace.yaml**
```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: agt-facturacao
```

**k8s/postgres-deployment.yaml**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: postgres
  namespace: agt-facturacao
spec:
  replicas: 1
  selector:
    matchLabels:
      app: postgres
  template:
    metadata:
      labels:
        app: postgres
    spec:
      containers:
      - name: postgres
        image: postgres:13
        ports:
        - containerPort: 5432
        env:
        - name: POSTGRES_DB
          value: agt_facturacao
        - name: POSTGRES_USER
          valueFrom:
            secretKeyRef:
              name: db-credentials
              key: username
        - name: POSTGRES_PASSWORD
          valueFrom:
            secretKeyRef:
              name: db-credentials
              key: password
        volumeMounts:
        - name: postgres-storage
          mountPath: /var/lib/postgresql/data
      volumes:
      - name: postgres-storage
        persistentVolumeClaim:
          claimName: postgres-pvc
```

**k8s/facturacao-deployment.yaml**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: facturacao-api
  namespace: agt-facturacao
spec:
  replicas: 3
  selector:
    matchLabels:
      app: facturacao-api
  template:
    metadata:
      labels:
        app: facturacao-api
    spec:
      containers:
      - name: facturacao-api
        image: your-registry/agt-facturacao:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: DB_HOST
          value: postgres
        - name: DB_PORT
          value: "5432"
        - name: DB_NAME
          value: agt_facturacao
        - name: DB_USER
          valueFrom:
            secretKeyRef:
              name: db-credentials
              key: username
        - name: DB_PASS
          valueFrom:
            secretKeyRef:
              name: db-credentials
              key: password
        livenessProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 20
          periodSeconds: 5
        resources:
          requests:
            cpu: "250m"
            memory: "512Mi"
          limits:
            cpu: "500m"
            memory: "1Gi"
---
apiVersion: v1
kind: Service
metadata:
  name: facturacao-api
  namespace: agt-facturacao
spec:
  selector:
    app: facturacao-api
  ports:
  - protocol: TCP
    port: 8080
    targetPort: 8080
  type: LoadBalancer
```

#### Step 3: Deploy to Kubernetes

```bash
# Create namespace and secrets
kubectl apply -f k8s/namespace.yaml
kubectl create secret generic db-credentials \
  --from-literal=username=agt_user \
  --from-literal=password=your_secure_password \
  -n agt-facturacao

# Apply all manifests
kubectl apply -f k8s/

# Verify deployment
kubectl get deployments -n agt-facturacao
kubectl get pods -n agt-facturacao
kubectl get svc -n agt-facturacao

# Check logs
kubectl logs -f deployment/facturacao-api -n agt-facturacao
```

---

### Cloud Platform Deployments

#### AWS ECS/Fargate

```bash
# Create ECR repositories
aws ecr create-repository --repository-name agt-facturacao
aws ecr create-repository --repository-name agt-pos
aws ecr create-repository --repository-name agt-stock
aws ecr create-repository --repository-name agt-users
aws ecr create-repository --repository-name agt-crm

# Build and push images
$(aws ecr get-login --no-include-email --region us-east-1)
docker build -f facturacao-api/Dockerfile -t agt-facturacao:latest .
docker tag agt-facturacao:latest <account-id>.dkr.ecr.us-east-1.amazonaws.com/agt-facturacao:latest
docker push <account-id>.dkr.ecr.us-east-1.amazonaws.com/agt-facturacao:latest
```

#### Azure Container Instances

```bash
# Create resource group
az group create --name agt-facturacao --location eastus

# Create container registry
az acr create --resource-group agt-facturacao --name agtregistry --sku Basic

# Build and push image
az acr build --registry agtregistry --image agt-facturacao:latest .

# Deploy to ACI
az container create \
  --resource-group agt-facturacao \
  --name agt-facturacao \
  --image agtregistry.azurecr.io/agt-facturacao:latest \
  --ports 8080 \
  --environment-variables DB_HOST=postgres DB_PORT=5432
```

#### Google Cloud Run

```bash
# Set project
gcloud config set project your-project-id

# Build and push to Cloud Build
gcloud builds submit --tag gcr.io/your-project-id/agt-facturacao

# Deploy to Cloud Run
gcloud run deploy agt-facturacao \
  --image gcr.io/your-project-id/agt-facturacao \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated \
  --set-env-vars DB_HOST=cloudsql-proxy,DB_PORT=5432
```

---

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: Maven Command Not Found

**Windows:**
```cmd
# Verify Maven installation
mvn -version

# If not found, add to PATH:
# 1. Right-click "This PC" → Properties → Advanced system settings → Environment Variables
# 2. Add Maven bin directory to PATH
# 3. Restart terminal
```

**macOS/Linux:**
```bash
# Check Maven location
which mvn

# If not found, reinstall:
# macOS
brew install maven

# Linux
sudo apt install maven
```

#### Issue 2: PostgreSQL Connection Error

```bash
# Check if PostgreSQL is running
# macOS
brew services list

# Linux
sudo systemctl status postgresql

# Windows (Services app)
# Look for PostgreSQL service and ensure it's running

# Test connection
psql -U agt_user -d agt_facturacao -h localhost
```

#### Issue 3: Port Already in Use

```bash
# Find process using port (Linux/macOS)
lsof -i :8080

# Kill process
kill -9 <PID>

# Windows: Find process using port
netstat -ano | findstr :8080

# Kill process
taskkill /PID <PID> /F
```

#### Issue 4: Docker Compose Issues

```bash
# Check Docker status
docker ps

# Restart Docker daemon
# macOS/Linux
sudo systemctl restart docker

# Windows
# Restart Docker Desktop application

# Check logs
docker-compose -f docker/docker-compose.yml logs postgres
```

#### Issue 5: Insufficient Memory

```bash
# Increase Maven memory allocation
# macOS/Linux
export MAVEN_OPTS="-Xmx2048m -XX:MaxPermSize=512m"

# Windows CMD
set MAVEN_OPTS=-Xmx2048m -XX:MaxPermSize=512m

# Windows PowerShell
$env:MAVEN_OPTS="-Xmx2048m -XX:MaxPermSize=512m"
```

#### Issue 6: Database Schema Not Initialized

```bash
# Manually run database setup
cd scripts
./setup-db.sh

# Or manually with psql
psql -U agt_user -d agt_facturacao -f schema.sql
```

### Log Locations

| Platform | Application Logs | Database Logs |
|----------|-----------------|---------------|
| Windows | `%AppData%\logs\` | `C:\Program Files\PostgreSQL\13\data\log\` |
| macOS | `~/Library/Logs/` | `/usr/local/var/log/postgres/` |
| Linux | `/var/log/` | `/var/log/postgresql/` |
| Docker | `docker logs <container-id>` | `docker logs <postgres-container-id>` |

---

## Environment Variables Reference

### Application Configuration

```bash
# Database Configuration
DB_HOST=localhost
DB_PORT=5432
DB_NAME=agt_facturacao
DB_USER=agt_user
DB_PASS=your_secure_password

# Server Configuration
SERVER_PORT=8080
SERVER_SERVLET_CONTEXT_PATH=/api/v1

# Logging
LOGGING_LEVEL_ROOT=INFO
LOGGING_LEVEL_AO_AGT=DEBUG

# Multilingual Support
SUPPORTED_LANGUAGES=PT,EN,FR,ZH
DEFAULT_LANGUAGE=PT
```

---

## Additional Resources

- [Maven Documentation](https://maven.apache.org/guides/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Docker Documentation](https://docs.docker.com/)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

---

## Support and Contribution

For issues, questions, or contributions, please open an issue on the [GitHub repository](https://github.com/muanza/repomultilingua).

---

**Last Updated:** May 2026  
**Version:** 1.0.0
