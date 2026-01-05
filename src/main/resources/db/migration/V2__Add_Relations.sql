ALTER TABLE projects
    ADD CONSTRAINT fk_projects_users
    FOREIGN KEY (owner_id) REFERENCES users(id);

ALTER TABLE tasks
    ADD CONSTRAINT fk_tasks_projects
    FOREIGN KEY (project_id) REFERENCES projects(id);
