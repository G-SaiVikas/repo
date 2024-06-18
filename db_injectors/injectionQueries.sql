use rpdb;

CREATE TABLE job (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company VARCHAR(255),
    job_title VARCHAR(255),
    job_description TEXT,
    skills_required VARCHAR(255),
    location VARCHAR(255),
    salary VARCHAR(255)
);

INSERT INTO job(company, job_title, job_description, skills_required, location, salary) VALUES
('Company A', 'Software Engineer', 'Develop and maintain software applications.', 'Java, Python, SQL', 'New York, NY', '80000'),
('Company B', 'Data Analyst', 'Analyze data and generate reports.', 'SQL, Excel, Python', 'San Francisco, CA', '70000'),
('Company C', 'Web Developer', 'Create and maintain websites.', 'HTML, CSS, JavaScript', 'Los Angeles, CA', '75000'),
('Company D', 'Project Manager', 'Manage software development projects.', 'Agile, Scrum, Project Management', 'Chicago, IL', '85000'),
('Company E', 'Systems Administrator', 'Maintain IT infrastructure and systems.', 'Linux, Networking, Troubleshooting', 'Houston, TX', '78000'),
('Company F', 'DevOps Engineer', 'Implement CI/CD pipelines and automation.', 'Jenkins, Docker, Kubernetes', 'Seattle, WA', '90000'),
('Company G', 'Database Administrator', 'Manage and optimize databases.', 'SQL, Oracle, MySQL', 'Boston, MA', '82000'),
('Company H', 'UX/UI Designer', 'Design user interfaces and experiences.', 'Sketch, Figma, Adobe XD', 'Austin, TX', '76000'),
('Company I', 'QA Engineer', 'Test software for bugs and issues.', 'Selenium, JUnit, TestNG', 'Denver, CO', '72000'),
('Company J', 'Network Engineer', 'Design and implement network solutions.', 'Cisco, Network Security, Routing', 'Phoenix, AZ', '80000'),
('Company K', 'Business Analyst', 'Analyze business requirements and processes.', 'Business Analysis, Requirements Gathering, UML', 'Miami, FL', '70000'),
('Company L', 'Security Engineer', 'Implement and maintain security measures.', 'Cybersecurity, Penetration Testing, SIEM', 'San Diego, CA', '88000'),
('Company M', 'Cloud Engineer', 'Manage cloud infrastructure and services.', 'AWS, Azure, Google Cloud', 'San Jose, CA', '92000'),
('Company N', 'Mobile Developer', 'Develop mobile applications.', 'iOS, Android, React Native', 'Dallas, TX', '83000'),
('Company O', 'IT Support Specialist', 'Provide technical support to users.', 'Windows, Troubleshooting, Customer Service', 'Atlanta, GA', '68000'),
('Company P', 'Product Manager', 'Oversee product development and strategy.', 'Product Management, Agile, Roadmapping', 'Orlando, FL', '86000'),
('Company Q', 'Software Architect', 'Design software architecture and solutions.', 'Microservices, Architecture, Design Patterns', 'Minneapolis, MN', '95000'),
('Company R', 'Technical Writer', 'Create technical documentation and guides.', 'Technical Writing, Documentation, API', 'Charlotte, NC', '70000'),
('Company S', 'AI Engineer', 'Develop and implement AI solutions.', 'Machine Learning, AI, Python', 'San Antonio, TX', '94000'),
('Company T', 'Game Developer', 'Develop and design video games.', 'Unity, Unreal Engine, C++', 'Las Vegas, NV', '89000');