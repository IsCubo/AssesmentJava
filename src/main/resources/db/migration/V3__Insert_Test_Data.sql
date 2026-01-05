DO $$
DECLARE
    u_id UUID;
    p_id UUID;
    t_id UUID;
    i INT;
    j INT;
    k INT;
    -- Hash para 'password' (BCrypt)
    hashed_pwd VARCHAR := '$2a$12$jCdhSIt2iR24UM2P002J5eS0FQPPPbMD.IFowg5zk./zuFNvOQ0Wm';
BEGIN
    FOR i IN 1..3 LOOP
        u_id := gen_random_uuid();
        INSERT INTO users (id, username, email, password) 
        VALUES (u_id, 'user'||i, 'user'||i||'@test.com', hashed_pwd);
        
        FOR j IN 1..7 LOOP
            p_id := gen_random_uuid();
            INSERT INTO projects (id, owner_id, name, status, deleted) 
            VALUES (p_id, u_id, 'Project '||j||' (User '||i||')', 'ACTIVE', false);
            
            FOR k IN 1..5 LOOP
                t_id := gen_random_uuid();
                INSERT INTO tasks (id, project_id, title, completed, deleted) 
                VALUES (t_id, p_id, 'Task '||k||' - Project '||j, (random() > 0.5), false);
            END LOOP;
        END LOOP;
    END LOOP;
END $$;
