---
name: erd
description: Generate or refresh the logical ER diagrams under docs/erd -- __<schema>.svg for the whole schema, _<group>.svg for each logical group and <table>.svg / <view>.svg for each table and view -- from the schema's model under .claude/skills/erd/models. Use when asked to create, update, regenerate or replace an ERD, entity diagram or schema diagram, and after a table, view, key or foreign key of the schema changes.
---

# Logical ERDs

The diagrams are logical: a table shows only the columns of its keys -- its primary key, its foreign keys and, for a
table with no primary key, the key that identifies a row (`AK`) -- and no types; a view shows its name alone. They are
SVG, from Graphviz, and generated: never hand-edit one; fix the model or the script and regenerate.

| File | Shows | Direction |
| ---- | ----- | --------- |
| `__<schema>.svg` | every table and the foreign keys among them | top to bottom |
| `_<group>.svg` | a logical group's tables and the foreign keys among them only | top to bottom |
| `<table>.svg` | the table, the tables it references and the tables referencing it, and its own foreign keys | left to right |
| `<view>.svg` | the view and the tables it reads, without their foreign keys | left to right |

The underscores keep the schema first and the groups next in a directory listing. A schema with no logical grouping
to speak of declares no group and gets no `_<group>.svg`.

## Generate

```shell
$ python3 .claude/skills/erd/scripts/generate_erd.py                     # every model under models/
$ python3 .claude/skills/erd/scripts/generate_erd.py models/<schema>.toml
```

`dot` must be on `PATH` (`brew install graphviz`), and Noto Sans KR installed: the text is set in it, the font the book's
PDF draws SVG text in, and Graphviz sizes each cell by measuring it. Each run rewrites every diagram of the model's `output`
directory and deletes the other `.svg` files there.

## The model

`models/<schema>.toml` holds what the diagrams show, read from the schema's DDL:

```toml
schema = "sakila"          # names __<schema>.svg
title = "sakila"
output = "docs/erd"        # relative to the repository root

[[group]]                  # a logical group; name names _<name>.svg, title is the book's caption
name = "films"
title = "영화"
tables = ["film", "film_text", "film_actor", "film_category", "actor", "category", "language"]

[table.film]
pk = ["film_id"]
[[table.film.fk]]
columns = ["original_language_id"]
references = "language"
nullable = true            # some column of the foreign key may be NULL
# ref_columns = [...]      # the referenced columns, when not the referenced table's primary key
# declared = false         # a join the schema relies on but no foreign key declares; drawn dotted

[view.film_list]
kind = "view"              # or "materialized view"
sources = ["film", "film_category", "category", "film_actor", "actor"]
```

## The notation

- A table's header is light; the table a diagram is about has a dark one. A view is a dashed box tagged `VIEW` or
  `MATERIALIZED VIEW`, and a dashed arrow goes from each table it reads to it.
- A row is a key column with its badge: `PK`, `FK`, `PK,FK` or `AK`. The column of a join no foreign key declares
  has no badge, and its line is dotted. A primary key column is underlined; a column of
  a nullable foreign key is in italics.
- A foreign key goes from the referencing column to the referenced one, in crow's foot. At the referencing end, zero or
  many, or zero or one when the foreign key is the whole primary key; at the referenced end, exactly one, or zero or
  one when the foreign key is nullable.
- Every diagram uses the same fonts and sizes, so an object comes out the same size wherever it is drawn. The script
  is the same file in every project that uses it; change it in one, copy it to the others.

## This project's models

One model per database, `models/<module>.toml`, writing into `<module>/docs/erd`:

- The models were read from the installed database's catalog (`sys.tables`, `sys.views`, `sys.key_constraints`,
  `sys.foreign_keys`, `sys.sql_expression_dependencies`), not from the scripts. A view that reads another view lists
  the tables under it.
- AdventureWorks' and WideWorldImporters' names are qualified by their schema (`Person.Person`), since WideWorldImporters
  repeats a table's name as a view's in `Website`; FutonManufacturing's, all in `dbo`, are not.
- The groups are logical and small, so that each diagram fits the book's page (about 500pt wide at most), and a group
  may repeat a table of another to show where the two meet. The whole-database diagrams are too wide for a page and
  are for the screen only.
- WideWorldImporters' 17 history tables (`*_Archive`), which have no key and are referenced by none, are left out.
- The file system is case-insensitive on macOS: when a group or object is renamed only in case, run the script twice,
  since the first run writes over the old file under its old name and then deletes it as stale.
